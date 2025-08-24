package com.fay.convention.utilities

import org.gradle.api.Project

internal fun Project.namespaceFromPath(): String {
    return buildString {
        append("com.fay")
        append(path.replace(":", "."))
    }
}
