object Config {
    fun namespace(module: String) = "me.elrevin.${module}"
    const val packageName = "me.elrevin.hopweather"

    const val compileSdk = 34
    const val minSdk = 26
    const val targetSdk = 34

    const val versionCode = 1
    const val versionName = "1.0"
    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    object Plugins {
        const val androidApplicationVersion = "8.1.1"
        const val kotlinAndroidVersion = "1.8.10"
        const val androidLibraryVersion = "8.1.1"
        const val kotlinJvmVersion = "1.8.10"
        const val hiltPlugin = "com.google.dagger.hilt.android"
        const val hiltPluginVersion = Dependecies.Hilt.version
        const val hiltPluginVlasspath = "com.google.dagger:hilt-android-gradle-plugin:${Dependecies.Hilt.version}"
    }
}