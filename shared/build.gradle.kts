plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization") version "1.9.0"
    id("com.android.library")
    id("com.squareup.sqldelight") //before i included the class path in project's build.gradle, declaring version 1.5.5 here worked.
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {//configuration for ios framework goes here.
        it.binaries.framework {
            baseName = "shared"
        }
    }

    val ktorVersion = "2.3.2"
    val sqlDelightVersion = "1.5.5"

    sourceSets {
        val commonMain by getting {
            dependencies {
                //put your multiplatform dependencies here
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
                //ktor
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion") //responsible for serializing/deserializing the content in a specific format
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion") //instructs ktor to use the JSON format and kotlinx.serialization as a serialization library

                //SqlDelight
                implementation("com.squareup.sqldelight:runtime:$sqlDelightVersion")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }

        val androidMain by getting{
            dependencies{
                implementation("io.ktor:ktor-client-android:$ktorVersion")
                //sqlDelight
                implementation("com.squareup.sqldelight:android-driver:$sqlDelightVersion")
            }
        }
        val iosMain by getting{
            dependencies{
                implementation("io.ktor:ktor-client-darwin:$ktorVersion")
                //sqlDelight
                implementation("com.squareup.sqldelight:native-driver:$sqlDelightVersion")
            }
        }
    }
}

android {// configuration for android library goes here.
    namespace = "com.example.kmm_test_2"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
}

sqldelight{
    database("AppDatabase"){
        packageName = "com.jetbrains.handson.kmm.shared.cache"
    }
}