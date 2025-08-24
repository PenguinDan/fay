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
            }

            with(libs) {
                implementation(kermit)

                implementation(ktor.client.contentNegotiation)
                api(ktor.client.core)
                implementation(ktor.client.logging)
                implementation(ktor.serialization.json)
            }
        }

        jvmCommonMain.dependencies {
            with(libs) {
                implementation(ktor.client.okhttp)
            }
        }

        iosMain.dependencies {
            with(libs) {
                implementation(ktor.client.darwin)
            }
        }
    }
}