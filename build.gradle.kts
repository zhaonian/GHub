import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    rootProject.extra["ci"] = rootProject.hasProperty("ci")
    repositories {
        google()
        gradlePluginPortal()
    }
    dependencies {
        classpath(deps.plugin.gradle)
        classpath(deps.plugin.kotlin)
        classpath(deps.plugin.safeArgs)
        classpath(deps.plugin.ktlint)
        classpath(deps.plugin.versions)
        classpath(deps.plugin.apollo)
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    apply {
        plugin("org.jlleitschuh.gradle.ktlint")
    }
    repositories {
        google()
        gradlePluginPortal()
        maven { setUrl("https://dl.bintray.com/kotlin/kotlin-eap") }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        allWarningsAsErrors = true
        jvmTarget = deps.versions.java.toString()
    }
}
