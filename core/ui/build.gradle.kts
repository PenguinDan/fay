plugins {
    with(libs.plugins) {
        id("com.fay.commonLibrary")
        id("com.fay.multiplatformUi")
        alias(buildConfig)
    }
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                with(projects) {
                    implementation(core.base)
                }
            }
        }
    }
}

compose {
    resources {
        publicResClass = true
    }
}
