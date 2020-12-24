plugins {
    kotlin("js")
}
group = "com.shopping"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
    maven {
        url = uri("https://dl.bintray.com/kotlin/kotlinx")
    }
    maven {
        url = uri("https://dl.bintray.com/subroh0508/maven")
    }
    maven {
        url = uri("https://dl.bintray.com/kotlin/kotlin-js-wrappers")
    }
    maven("https://dl.bintray.com/samgarasx/kotlin-js-wrappers")
}
dependencies {
    implementation(project(":shared"))
    implementation("org.jetbrains.kotlinx:kotlinx-html-js:0.7.2")
    implementation("org.jetbrains:kotlin-react:16.13.1-pre.124-kotlin-1.4.10")
    implementation("org.jetbrains:kotlin-react-dom:16.13.1-pre.124-kotlin-1.4.10")
    implementation("org.jetbrains:kotlin-styled:1.0.0-pre.110-kotlin-1.4.10")
//    implementation("net.subroh0508.kotlinmaterialui:core:0.5.3")
//    implementation("net.subroh0508.kotlinmaterialui:lab:0.5.3")
//    implementation("com.github.samgarasx:kotlin-antd:3.20.3-pre.3-kotlin-1.4.0")
//    implementation(npm("antd", "4.7.3"))
//    implementation(npm("react-icons", "3.11.0"))
//    implementation(npm("@material-ui/icons", "4.9.1"))
//    implementation(npm("@material-ui/core", "4.9.1"))

}
kotlin {
    js {
        browser {
            binaries.executable()
            webpackTask {
                cssSupport.enabled = true
            }
            runTask {
                cssSupport.enabled = true
            }
            testTask {
                useKarma {
                    useChromeHeadless()
                    webpackConfig.cssSupport.enabled = true
                }
            }
        }
    }
}