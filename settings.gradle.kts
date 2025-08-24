@file:Suppress("UnstableApiUsage")

rootProject.name = "Fay"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    includeBuild("build-logic")
    repositories {
        mavenCentral()
        google()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        google()
        maven(url = "https://aws.oss.sonatype.org/content/repositories/snapshots/")
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

include(
    ":composeApp",

    ":core:authentication",
    ":core:base",
    ":core:network",
    ":core:testing",
    ":core:ui",

    ":data",

    ":ui:appointments",
    ":ui:chat",
    ":ui:home",
    ":ui:journal",
    ":ui:onboarding",
    ":ui:profile",
)
