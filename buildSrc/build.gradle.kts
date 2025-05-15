plugins {
    `kotlin-dsl`
}

projectDir.parentFile
    .resolve("gradle.properties")
    .readLines()
    .filter { it.contains("Version") }
    .forEach { line ->
        val pieces = line.split("=").map { it.trim() }
        project.extra[pieces.first()] = pieces.last()
    }

repositories {
    gradlePluginPortal()
}

val spotbugsPluginVersion: String by project
val spotlessPluginVersion: String by project
val errorpronePluginVersion: String by project

dependencies {
    implementation("com.github.spotbugs:com.github.spotbugs.gradle.plugin:$spotbugsPluginVersion")
    implementation("com.diffplug.spotless:com.diffplug.spotless.gradle.plugin:$spotlessPluginVersion")
    implementation("net.ltgt.errorprone:net.ltgt.errorprone.gradle.plugin:$errorpronePluginVersion")
}
