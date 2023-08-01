plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization") version "1.8.21"
    id("com.android.library")
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
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    val ktorVersion = "2.3.2"

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
            }
        }
        val iosMain by getting{
            dependencies{
                implementation("io.ktor:ktor-client-darwin:$ktorVersion")
            }
        }
    }
}

android {
    namespace = "com.example.kmm_test_2"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
}