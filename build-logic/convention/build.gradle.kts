plugins {
    `kotlin-dsl`
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

dependencies {
    with(libs) {
        compileOnly(android.gradlePlugin)
        compileOnly(android.kmp.library.gradlePlugin)
        compileOnly(buildConfig.gradlePlugin)
        compileOnly(composeMultiplatform.gradlePlugin)
        compileOnly(detekt.gradlePlugin)
        compileOnly(kotlin.gradlePlugin)
        compileOnly(kotlin.gradlePlugin.api)
        compileOnly(kotlin.ksp.gradlePlugin)
    }
}

gradlePlugin {
    plugins {
        register("commonApplication") {
            id = "com.fay.commonApplication"
            implementationClass = "com.fay.convention.CommonApplicationPlugin"
        }
        register("commonLibrary") {
            id = "com.fay.commonLibrary"
            implementationClass = "com.fay.convention.CommonLibraryPlugin"
        }
        register("kotlinMultiplatform") {
            id = "com.fay.kotlinMultiplatform"
            implementationClass = "com.fay.convention.KotlinMultiplatformPlugin"
        }
        register("multiplatformUi") {
            id = "com.fay.multiplatformUi"
            implementationClass = "com.fay.convention.MultiplatformUiPlugin"
        }
    }
}
