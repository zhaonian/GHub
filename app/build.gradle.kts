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
//    kotlinOptions {
//        jvmTarget = deps.versions.java.toString()
//    }
    sourceSets {
        val commonTest = "src/test/java"
        getByName("androidTest").java.srcDirs(commonTest)
        getByName("test").java.srcDirs(commonTest)
    }
    splits {
        abi {
            isEnable = true
            reset()
            if (rootProject.extra["ci"] as Boolean || gradle.startParameter.taskNames.any { it.contains("release") }) {
                include("armeabi", "armeabi-v7a", "armeabi-v8a", "arm64-v8a", "mips", "mips64", "x86", "x86_64")
            } else {
                include("arm64-v8a", "x86_64", "x86")
            }
        }
    }
    testOptions {
        animationsDisabled = true
        unitTests.apply {
            isReturnDefaultValues = true
            isIncludeAndroidResources = true
        }
    }
    allprojects {
        apply("$rootDir/app/build-options.gradle")
    }
}

dependencies {
    implementation(deps.android.core)
    implementation(deps.kotlin.stdlib)
    implementation(deps.kotlin.coroutines)

    // App compat
    implementation(deps.android.appcompat)
    implementation(deps.android.lifecycle)
    implementation(deps.android.lifecycleExtension)

    // Navigation library
    implementation(deps.android.navigation.fragment)
    implementation(deps.android.navigation.ui)

    // Preferences
    implementation(deps.android.preference.preference)

    // UI
    implementation(deps.android.ui.constraintlayout)
    implementation(deps.android.ui.material)

    // Dagger
    implementation(deps.di.dagger)
    kapt(deps.di.daggerCompiler)
    implementation(deps.di.daggerAndroid)
    implementation(deps.di.daggerAndroidSupport)
    kapt(deps.di.daggerAndroidProcessor)

    // Retrofit
    implementation(deps.network.retrofit)
    implementation(deps.network.retrofitMoshiConverter)

    // Apollo
    implementation(deps.network.apollo)

    // Timber
    implementation(deps.log.timber)

    // Leak Canary
    debugImplementation(deps.codeAnalysis.leakCanary)

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

// TODO: move this into android when https://github.com/gradle/kotlin-dsl-samples/issues/1368
//  resolved.
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}
