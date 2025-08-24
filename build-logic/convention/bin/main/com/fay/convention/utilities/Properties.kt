package com.fay.convention.utilities

import org.gradle.api.Project
import java.util.Properties

fun Project.getGradlePropertyString(propertyName: String): String {
    return requireNotNull(properties[propertyName]?.toString()) {
        "Property with name $propertyName missing from gradle.properties file"
    }
}

/**
 * Retrieve the latest value for [propertyName] or throws if no value has been defined for the property or if it is absent
 */
fun Properties.require(propertyName: String): String {
    return requireNotNull(getProperty(propertyName)) {
        "Unexpected null value for $propertyName. Do you have the latest properties files?"
    }
}
