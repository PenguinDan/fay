package com.fay.convention.utilities

import org.gradle.api.Project
import java.io.FileInputStream
import java.io.InputStreamReader
import java.lang.ref.WeakReference
import java.util.Properties

enum class Brand {
    IH,
    DOD,
}

enum class Environment {
    UAT,
    PROD,
}

fun Project.getBrand(): Brand {
    return Brand.valueOf(getGradlePropertyString("project.brand"))
}

fun Project.getEnvironment(): Environment {
    return Environment.valueOf(getGradlePropertyString("project.environment"))
}

internal fun Project.namespaceFromPath(): String {
    return buildString {
        append("com.fay")
        append(path.replace(":", "."))
    }
}

private var cachedKeysProperties: WeakReference<Properties>? = null

private fun Project.getKeysProperties(): Properties {
    val cachedProperties = cachedKeysProperties?.get()
    if (cachedProperties != null) return cachedProperties

    val keyProperties = Properties()
    val keyPropertiesFile = rootProject.file("keys.properties")

    if (keyPropertiesFile.isFile) {
        InputStreamReader(FileInputStream(keyPropertiesFile), Charsets.UTF_8).use { reader ->
            keyProperties.load(reader)
        }
    } else {
        error("$keyPropertiesFile file not found.")
    }

    return keyProperties.apply {
        cachedKeysProperties = WeakReference(keyProperties)
    }
}

fun Project.getKeysPropertiesValue(keyName: String): String {
    val brand = getBrand()
    val environment = getEnvironment()

    val propertyNameWithBrandAndEnv = "${brand}_${environment}_$keyName"
    val propertyNameWithBrand = "${brand}_$keyName"
    val propertyNameWithEnv = "${environment}_$keyName"

    return getKeysProperties().run {
        getProperty(propertyNameWithBrandAndEnv)
            ?: getProperty(propertyNameWithBrand)
            ?: getProperty(propertyNameWithEnv)
            ?: require(keyName)
    }
}
