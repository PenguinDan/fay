plugins {
    with(libs.plugins) {
        id("com.fay.commonLibrary")
    }
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            with(libs) {
                api(assertk)

                api(kotlin.test)
            }
        }
    }
}
