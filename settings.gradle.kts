import kotlin.io.path.exists
import kotlin.io.path.forEachDirectoryEntry
import kotlin.io.path.name

rootProject.name = "cses-java"

rootDir.toPath().forEachDirectoryEntry {
    if (it.resolve("build.gradle.kts").exists() && it.name != "buildSrc") {
        include(it.name)
    }
}

