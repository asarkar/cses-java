import com.github.spotbugs.snom.SpotBugsTask
import net.ltgt.gradle.errorprone.errorprone

plugins {
    `java-library`
    id("com.github.spotbugs")
    id("com.diffplug.spotless")
    id("net.ltgt.errorprone")

}

val javaVersion = providers
    .fileContents(layout.settingsDirectory.file(".java-version"))
    .asText
    .map { v -> JavaLanguageVersion.of(v.trim()) }

java {
    toolchain {
        languageVersion = javaVersion
    }
}

repositories {
    mavenCentral()
    maven("https://jitpack.io")
}

val junit5Version: String by project
val errorproneVersion: String by project
val jdataVersion: String by project
val assertjVersion: String by project
val spotbugsVersion: String by project

dependencies {
//    compileOnly("com.github.spotbugs:spotbugs-annotations:$spotbugsVersion")
    testImplementation(platform("org.junit:junit-bom:$junit5Version"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("com.asarkar.jdata:junit:$jdataVersion")
    testImplementation("org.assertj:assertj-core:$assertjVersion")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    errorprone("com.google.errorprone:error_prone_core:$errorproneVersion")
}

spotbugs {
    toolVersion = spotbugsVersion
    excludeFilter = layout.settingsDirectory.dir("conf").file("spotbugs-exclude.xml")
}

val palantirJavaFmtVersion: String by project

spotless {
    java {
        palantirJavaFormat(palantirJavaFmtVersion)
        toggleOffOn()
    }
}

val ci = providers.environmentVariable("CI")

/*
DO NOT use existing(Task::class) for configuring tasks.
existing() if not referenced from another task is dropped on the floor!
Use withTask() instead.
*/
tasks {
    withType(JavaCompile::class) {
        options.apply {
            compilerArgs.addAll(listOf("-Werror"))
            errorprone {
                disable("DefaultPackage", "StringSplitter")
            }
        }
    }

    test {
        useJUnitPlatform()
        testLogging {
            showStandardStreams = true
        }
        maxHeapSize = "512m"
        // Suppress warning: Sharing is only supported for boot loader classes...
        // https://stackoverflow.com/q/54205486/839733
        jvmArgs("-Xshare:off")
    }

    withType<SpotBugsTask> {
        reports.create("html") {
            required = !ci.isPresent
        }
    }

    val spotlessApply by existing {
        enabled = !ci.isPresent
    }

    named("spotlessCheck") {
        dependsOn(spotlessApply)
    }
}
