package com.fay.convention

import com.google.devtools.ksp.gradle.KspExtension
import com.fay.convention.utilities.findPluginId
import com.fay.convention.utilities.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.get
import org.jetbrains.compose.ComposePlugin
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import com.fay.convention.utilities.addKspDependencyForAllTargets

class MultiplatformUiPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        configureCompose()
        configureCircuit()
    }

    private fun Project.configureCompose() {
        with(pluginManager) {
            with(libs) {
                apply(findPluginId("compose-multiplatform"))
                apply(findPluginId("kotlin-composeCompiler"))
                apply(findPluginId("compose-hotReload"))
            }
        }

        val composeDependencies = ComposePlugin.Dependencies(this)
        extensions.configure<KotlinMultiplatformExtension> {
            sourceSets.commonMain.dependencies {
                implementation(composeDependencies.components.resources)
                implementation(composeDependencies.components.uiToolingPreview)

                implementation(composeDependencies.foundation)

                implementation(composeDependencies.material3)

                implementation(composeDependencies.runtime)

                implementation(composeDependencies.ui)
            }

            sourceSets.androidMain.dependencies {
                implementation(composeDependencies.preview)

                implementation(libs.findLibrary("androidx-activity-compose").get())
            }

            sourceSets["desktopMain"].dependencies {
                implementation(composeDependencies.desktop.currentOs)
            }
        }
    }

    private fun Project.configureCircuit() {
        extensions.configure<KotlinMultiplatformExtension> {
            sourceSets.commonMain.dependencies {
                implementation(libs.findLibrary("circuit-codegen-annotations").get())
                implementation(libs.findLibrary("circuit-foundation").get())
            }

            sourceSets.androidMain.dependencies {
                implementation(libs.findLibrary("circuit-android").get())
            }

            addKspDependencyForAllTargets(libs.findLibrary("circuit-codegen-compiler").get())
        }

        extensions.configure<KspExtension> {
            arg("circuit.codegen.mode", "kotlin_inject_anvil")
            arg("circuit.codegen.lenient", "true")

            useKsp2.set(false)
        }
    }
}
