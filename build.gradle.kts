buildscript {
    repositories {
        google()
        jcenter()
        maven { setUrl("https://dl.bintray.com/kotlin/kotlin-eap") }
    }
    dependencies {
        classpath("com.android.tools.build:gradle:4.0.0-alpha01")
        kotlin("gradle-plugin", version = "1.3.50")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.1.0")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven { setUrl("https://dl.bintray.com/kotlin/kotlin-eap") }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
