plugins {
    with(libs.plugins) {
        id("com.fay.commonLibrary")
    }
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            with(libs) {
                implementation(androidx.datastorePreferences)

                implementation(kermit)
            }
        }

        commonTest.dependencies {
            with(libs) {
                implementation(kotlin.test)
            }
        }
    }
}
