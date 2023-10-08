import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
val apiKey: String = gradleLocalProperties(rootDir).getProperty("apiKey")
plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = Config.namespace("recipes_presentation")
    compileSdk = Config.compileSdk

    defaultConfig {
        minSdk = Config.minSdk
        buildConfigField("String", "apiKey", apiKey)

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
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
}


dependencies {
    other()
    compose()

    test()
}