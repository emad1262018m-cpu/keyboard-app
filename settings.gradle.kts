pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "KeyboardApp"

include(":app")
include(":core:keyboard")
include(":core:input")
include(":core:ui")
include(":data:settings")
include(":feature:settings_ui")
