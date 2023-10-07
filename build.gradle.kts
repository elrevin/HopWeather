plugins {
    id("com.android.application") version Config.Plugins.androidApplicationVersion apply false
    id("org.jetbrains.kotlin.android") version Config.Plugins.kotlinAndroidVersion apply false
    id("com.android.library") version Config.Plugins.androidLibraryVersion apply false
    id("com.google.dagger.hilt.android") version Dependecies.Hilt.version apply false
    id("org.jetbrains.kotlin.jvm") version Config.Plugins.kotlinJvmVersion apply false
}