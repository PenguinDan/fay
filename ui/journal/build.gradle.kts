plugins {
    with(libs.plugins) {
        id("com.fay.commonLibrary")
        id("com.fay.multiplatformUi")
    }
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            with(projects) {
                implementation(core.authentication)
                implementation(core.base)
                implementation(core.ui)
            }
        }
    }
}
