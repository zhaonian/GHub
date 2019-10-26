import org.jetbrains.kotlin.config.KotlinCompilerVersion

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
    id("androidx.navigation.safeargs")
}

android {
    compileSdkVersion(deps.build.compileSdk)
    defaultConfig {
        applicationId = "io.zluan.ghub"
        minSdkVersion(deps.build.minSdk)
        targetSdkVersion(deps.build.targetSdk)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = deps.versions.java
        targetCompatibility = deps.versions.java
    }
    kotlinOptions {
        jvmTarget = deps.versions.java.toString()
    }
}

dependencies {
    implementation(deps.android.core)
    implementation(deps.kotlin.stdlib)
    implementation(deps.kotlin.coroutines)

    // App compat
    implementation(deps.android.appcompat)
    implementation(deps.android.lifecycle)

    // Navigation library
    implementation(deps.android.navigation.fragment)
    implementation(deps.android.navigation.ui)

    // UI
    implementation(deps.android.ui.constraintlayout)
    implementation(deps.android.ui.material)

    // Unit testing
    testImplementation(deps.android.test.junit)
    testImplementation(deps.android.test.runner)
    testImplementation(deps.android.test.core)
    testImplementation(deps.test.robolectric)

    // Instrumented testing
    androidTestImplementation(deps.android.test.core)
    androidTestImplementation(deps.android.test.junit)
    androidTestImplementation(deps.android.test.runner)
    androidTestImplementation(deps.android.test.core)
}
