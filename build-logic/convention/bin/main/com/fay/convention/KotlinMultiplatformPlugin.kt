package com.fay.convention

import com.fay.convention.utilities.findPluginId
import com.fay.convention.utilities.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.jvm.toolchain.JavaLanguageVersion
import org.gradle.kotlin.dsl.*
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask
import com.fay.convention.utilities.addKspDependencyForAllTargets
import io.gitlab.arturbosch.detekt.extensions.DetektExtension

class KotlinMultiplatformPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        configureKotlin()
        configureKotlinTesting()
        configureKotlinLint()
        configureTargets()
        configureKotlinInject()

        return@with Unit
    }

    private fun Project.configureKotlin() {
        with(pluginManager) {
            apply(libs.findPluginId("kotlin-multiplatform"))
            apply(libs.findPluginId("kotlin-ksp"))
        }

        // Configure Java
        extensions.configure<JavaPluginExtension> {
            toolchain {
                languageVersion.set(JavaLanguageVersion.of(17))
            }
        }

        tasks.withType<KotlinCompilationTask<*>>().configureEach {
            compilerOptions {
                freeCompilerArgs.add("-Xexpect-actual-classes")
                optIn.addAll(
                    "kotlinx.coroutines.FlowPreview",
                    "kotlin.experimental.ExperimentalObjCName",
                    "kotlin.experimental.ExperimentalObjCRefinement",
                )
            }
        }
    }

    private fun Project.configureKotlinLint() {
        with(pluginManager) {
            apply(libs.findPluginId("detekt"))
        }

        extensions.apply {
            configure<DetektExtension> {
                basePath = projectDir.absolutePath
                config.setFrom(rootDir.path + "/config/detekt/detekt.yml")
                parallel = true
                buildUponDefaultConfig = true
                allRules = false
                autoCorrect = true
                source.setFrom(
                    files(
                        "src/androidMain/kotlin",
                        "src/commonMain/kotlin",
                        "src/desktopMain/kotlin",
                        "src/iosMain/kotlin",
                        "src/jvmCommonMain/kotlin",
                    ),
                )
            }

            configure<KotlinMultiplatformExtension> {
                dependencies {
                    "detektPlugins".invoke(libs.findLibrary("detekt-formattingRules").get())
                    "detektPlugins".invoke(libs.findLibrary("detekt-composeRules").get())
                }
            }
        }
    }

    private fun Project.configureKotlinTesting() {
        extensions.configure<KotlinMultiplatformExtension> {
            sourceSets.commonTest.dependencies {
                implementation(project(":core:testing"))
            }
        }
    }

    private fun Project.configureTargets() {
        extensions.configure<KotlinMultiplatformExtension> {
            applyDefaultHierarchyTemplate()

            // Create and configure targets. https://kotlinlang.org/docs/multiplatform-discover-project.html#targets
            sourceSets.create("jvmCommonMain") {
                dependsOn(sourceSets.getByName("commonMain"))
            }
            jvm("desktop")
            androidTarget()
            iosArm64()
            iosSimulatorArm64()

            listOf("desktopMain", "androidMain").forEach {
                sourceSets.getByName(it).dependsOn(sourceSets.getByName("jvmCommonMain"))
            }
        }
    }

    private fun Project.configureKotlinInject() {
        extensions.configure<KotlinMultiplatformExtension> {
            sourceSets.commonMain.dependencies {
                implementation(libs.findLibrary("kotlinInject-runtime").get())
                implementation(libs.findLibrary("kotlinInject-anvil-runtime").get())
                implementation(libs.findLibrary("kotlinInject-anvil-runtimeOptional").get())
            }

            addKspDependencyForAllTargets(libs.findLibrary("kotlinInject-compiler").get())
            addKspDependencyForAllTargets(libs.findLibrary("kotlinInject-anvil-compiler").get())
        }
    }
}
