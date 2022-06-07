val kotlinVersion = "1.6.10"
val serializationVersion = "1.3.3"
val reactVersion = "18.0.0-pre.332-kotlin-1.6.21"
val kotlinEmotion = "11.9.0-pre.332-kotlin-1.6.21"
val coroutinesVersion = "1.6.2"
val reactYtLiteVersion = "1.0.4"
val reactShareVersion = "4.4.0"
val kotlinSyleVersion = "5.3.5-pre.341-compat"

plugins {
    kotlin("js") version "1.6.21"
    kotlin("plugin.serialization") version "1.6.21"
}

group = "me.bmkil"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.jetbrains.kotlin-wrappers:kotlin-emotion:$kotlinEmotion")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-react:$reactVersion")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-react-dom:$reactVersion")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationVersion")

    //Video Player
    implementation(npm("@lite-embed/react", reactYtLiteVersion))

    // Share
    implementation(npm("react-share", reactShareVersion))

}

kotlin {
    js(LEGACY) {
        binaries.executable()
        browser {
            commonWebpackConfig {
                cssSupport.enabled = true
            }
        }
    }
}

tasks.create("stage") {
    dependsOn(tasks.getByName("build"))
}