package com.fay.convention.utilities

import com.github.gmazzo.buildconfig.BuildConfigExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.buildConfigField
import org.gradle.kotlin.dsl.configure

internal fun Project.configureBuildConfig() {
    pluginManager.apply(libs.findPluginId("buildConfig"))

    extensions.configure<BuildConfigExtension> {
        packageName(namespaceFromPath())
    }
}

data class BuildConfigKeysConstructor(
    val className: String = "BuildConfig",
    val keyNames: List<String>,
)

fun Project.addBuildConfigKeys(vararg buildConfigKeysConstructor: BuildConfigKeysConstructor) {
    extensions.configure<BuildConfigExtension> {
        buildConfigKeysConstructor.forEach { keysConstructor ->
            forClass(keysConstructor.className) {
                keysConstructor.keyNames.forEach { keyName ->
                    val propertyValue = getKeysPropertiesValue(keyName)
                    buildConfigField(keyName, propertyValue)
                }
            }
        }
    }
}
