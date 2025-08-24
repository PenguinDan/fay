plugins {
    with(libs.plugins) {
        id("com.fay.commonLibrary")
        alias(kotlin.serialization)
    }
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            with(projects) {
                implementation(core.base)
                implementation(core.network)
            }

            with(libs) {
                implementation(kotlinx.datetime)
            }
        }
    }
}

buildConfig {
    useKotlinOutput {
        internalVisibility = false
    }
}
