package com.fay.convention.utilities

import com.github.gmazzo.buildconfig.BuildConfigExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

internal fun Project.configureBuildConfig() {
    pluginManager.apply(libs.findPluginId("buildConfig"))

    extensions.configure<BuildConfigExtension> {
        packageName(namespaceFromPath())
    }
}
