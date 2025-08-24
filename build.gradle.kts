plugins {
    with(libs.plugins) {
        alias(android.application) apply false
        alias(android.library) apply false
        alias(android.kmp.library) apply false
        alias(buildConfig) apply false
        alias(compose.hotReload) apply false
        alias(compose.multiplatform) apply false
        alias(detekt) apply false
        alias(kotlin.composeCompiler) apply false
        alias(kotlin.ksp) apply false
        alias(kotlin.multiplatform) apply false
        alias(kotlin.serialization) apply false
    }
}
