import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    kotlin("jvm") version "2.1.20"
    kotlin("plugin.serialization") version "2.1.20"
    application
    java
}

group = "dev.kumchatka"
version = "1.0.0"

repositories {
    mavenCentral()
    maven("https://jitpack.io")
}

dependencies {
    implementation("io.github.kotlin-telegram-bot.kotlin-telegram-bot:telegram:6.3.0")

    implementation("org.slf4j:slf4j-simple:2.0.16")

    implementation("com.natpryce:konfig:1.6.10.0")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.1")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.8.0")

    implementation("org.hihn:listenbrainz-client:1.5.0")
    implementation("com.fasterxml.jackson.core:jackson-core:2.15.0-rc1")
    implementation("com.squareup.okio:okio-jvm:3.4.0")
}

tasks.wrapper {
    gradleVersion = "8.10"
}

tasks.run {
    println("[gradle] Version code: ${project.version}")
}

kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_21)
    }
}

application {
    mainClass.set("MainKt")
}
