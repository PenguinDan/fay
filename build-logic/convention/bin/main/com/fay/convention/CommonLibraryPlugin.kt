package com.fay.convention

import com.android.build.gradle.BaseExtension
import com.fay.convention.utilities.AndroidVersions
import com.fay.convention.utilities.configureBuildConfig
import com.fay.convention.utilities.configureParcelize
import com.fay.convention.utilities.findPluginId
import com.fay.convention.utilities.libs
import com.fay.convention.utilities.namespaceFromPath
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

//@Suppress("UnstableApiUsage")
class CommonLibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        configureAndroid()
        configureKotlinMultiplatform()
        configureParcelize()
        configureBuildConfig()
    }

    private fun Project.configureAndroid() {
        with(pluginManager) {
            apply(libs.findPluginId("android-library"))
            // Can't add this until Parcelize works with library
//            apply(libs.findPluginId("android-kmp-library"))
        }

        extensions.configure<BaseExtension> {
            namespace = namespaceFromPath()
            compileSdkVersion(AndroidVersions.COMPILE_SDK)

            defaultConfig {
                minSdk = AndroidVersions.MIN_SDK
                targetSdk = AndroidVersions.TARGET_SDK

                testInstrumentationRunner = "androidx.test.runner.AndroidJunitRunner"
            }
        }

//        extensions.configure<KotlinMultiplatformExtension> {
            // Can't add this until Parcelize works with library
//            androidLibrary {
//                namespace = namespaceFromPath()
//
//                compileSdk = AndroidVersions.COMPILE_SDK
//                minSdk = AndroidVersions.MIN_SDK
//
//                // Enable Java compilation support
//                withJava()
//                // Enable unit and instrumentation tests
//                withHostTestBuilder { }.configure { }
//                withDeviceTestBuilder { sourceSetTreeName = "test" }
//            }
//        }
    }

    private fun Project.configureKotlinMultiplatform() {
        with(pluginManager) {
            apply("com.fay.kotlinMultiplatform")
        }
    }
}
