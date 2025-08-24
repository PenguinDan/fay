package com.fay.convention.utilities

import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension


/**
 * Shared configuration between Android applications and library
 */
internal fun Project.configureParcelize() {
    with(pluginManager) {
        apply(libs.findPluginId("kotlin-parcelize"))
    }

    extensions.configure<KotlinMultiplatformExtension> {
        androidTarget {
            compilerOptions {
                freeCompilerArgs.addAll(
                    "-P", "plugin:org.jetbrains.kotlin.parcelize:additionalAnnotation=com.fay.core.base.kotlin.CommonParcelize",
                )
            }
        }
    }
}
