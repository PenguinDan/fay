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
                implementation(core.base)
                implementation(core.ui)

                implementation(data)
            }

            with(libs) {
                implementation(kotlinx.datetime)
            }
        }
    }
}
