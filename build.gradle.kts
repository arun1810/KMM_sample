plugins {
    //trick: for the same plugin versions in all sub-modules
    id("com.android.application").version("8.0.0").apply(false)
    id("com.android.library").version("8.0.0").apply(false)
    kotlin("android").version("1.9.0").apply(false)
    kotlin("multiplatform").version("1.9.0").apply(false)
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

buildscript {
    repositories{
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies{
        classpath("com.squareup.sqldelight:gradle-plugin:1.5.5")
    }
}
