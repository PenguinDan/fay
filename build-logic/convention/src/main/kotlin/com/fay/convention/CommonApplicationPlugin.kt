package com.fay.convention

import com.android.build.gradle.BaseExtension
import com.fay.convention.utilities.AndroidVersions
import com.fay.convention.utilities.configureBuildConfig
import com.fay.convention.utilities.configureParcelize
import com.fay.convention.utilities.findPluginId
import com.fay.convention.utilities.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class CommonApplicationPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        configureAndroid()
        configureKotlinMultiplatform()
        configureParcelize()
        configureBuildConfig()

        return@with Unit
    }

    private fun Project.configureAndroid() {
        with(pluginManager) {
            apply(libs.findPluginId("android-application"))
        }

        extensions.configure<BaseExtension> {
            compileSdkVersion(AndroidVersions.COMPILE_SDK)

            defaultConfig {
                minSdk = AndroidVersions.MIN_SDK
                targetSdk = AndroidVersions.TARGET_SDK

                testInstrumentationRunner = "androidx.test.runner.AndroidJunitRunner"
            }
        }
    }

    private fun Project.configureKotlinMultiplatform() {
        with(pluginManager) {
            apply("com.fay.kotlinMultiplatform")
        }
    }
}
