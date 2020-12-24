import org.jetbrains.kotlin.config.LanguageFeature

plugins {
    kotlin("multiplatform")
    id("com.android.library")
}
group = "com.shopping"
version = "1.0-SNAPSHOT"

val ktor_version = "1.4.1"
val kotest_version = "4.2.0"

repositories {
    gradlePluginPortal()
    google()
    jcenter()
    mavenCentral()
    maven(url = "https://dl.bintray.com/ekito/koin")
    maven(url = "https://kotlin.bintray.com/kotlinx/")

}
kotlin {
    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_1_8.toString()
                suppressWarnings = true
            }
        }
    }
    js("frontend") {
        browser {
            binaries.executable()
            testTask {
                useKarma {
                    useFirefox()
                }
            }
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-core:$ktor_version")
                implementation("io.ktor:ktor-client-logging:$ktor_version")
                implementation("io.ktor:ktor-client-json:$ktor_version")
                implementation("io.ktor:ktor-client-serialization:$ktor_version")
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.1.0")
                api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.0")
            }
        }
        val commonTest by getting {
            dependencies {
//                implementation("io.kotest:kotest-assertions:$kotest_version")
//                implementation("io.kotest:kotest-property:$kotest_version")
//                implementation(kotlin("test-common"))
//                implementation(kotlin("test-annotations-common"))
//                implementation("io.ktor:ktor-client-mock:$ktor_version")
//                api("io.mockk:mockk:1.10.0")
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-serialization-jvm:$ktor_version")
                implementation("io.ktor:ktor-client-json-jvm:$ktor_version")
                implementation("io.ktor:ktor-client-logging-jvm:$ktor_version")
                implementation("io.ktor:ktor-client-android:$ktor_version")
                implementation("androidx.core:core-ktx:1.3.2")
                api("org.jetbrains.kotlinx:kotlinx-datetime:0.1.0")
                api("androidx.datastore:datastore-preferences:1.0.0-alpha02")
            }
        }
        val androidTest by getting {
            dependencies {
//                implementation("io.kotest:kotest-runner-junit5-jvm:$kotest_version")
//                implementation("io.ktor:ktor-client-mock-jvm:$ktor_version")
            }
        }
        val frontendMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-serialization-js:$ktor_version")
                implementation("io.ktor:ktor-client-json-js:$ktor_version")
                implementation("io.ktor:ktor-client-logging-js:$ktor_version")
                implementation("io.ktor:ktor-client-js:$ktor_version")
//                implementation("io.kotest:kotest-framework-engine:$kotest_version")
                api("org.jetbrains.kotlinx:kotlinx-datetime:0.1.0")
            }
        }
        val frontendTest by getting {
            dependencies {
//                implementation("io.ktor:ktor-client-mock-js:$ktor_version")
//                implementation(kotlin("test-js"))
            }
        }
        all {
            languageSettings.enableLanguageFeature(LanguageFeature.AllowResultInReturnType.toString())
            languageSettings.enableLanguageFeature(LanguageFeature.InlineClasses.toString())
        }
    }
}
android {
    compileSdkVersion(30)
    defaultConfig {
        minSdkVersion(23)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    buildToolsVersion = "30.0.2"
}