plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id(Config.Plugins.hiltPlugin)
    kotlin("kapt")
}

android {
    namespace = Config.namespace("domain")
    compileSdk = Config.compileSdk

    compileSdk = Config.compileSdk

    defaultConfig {
        minSdk = Config.minSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlin {
        jvmToolchain(17)
    }
}

dependencies {
    coreModule()
    other()
    test()
    hilt()
}