import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    with(libs.plugins) {
        id("com.fay.commonApplication")
        id("com.fay.multiplatformUi")
    }
}

kotlin {
    targets.withType<KotlinNativeTarget>().configureEach {
        binaries.framework {
            baseName = "ComposeApp"
            isStatic = true

            with(projects) {
                export(core.authentication)
                export(core.base)
            }
        }
    }

    sourceSets {
        val desktopMain by getting

        commonMain.dependencies {
            with(projects) {
                api(core.authentication)
                api(core.base)
                api(core.network)
                api(core.ui)

                api(data)

                api(ui.appointments)
                api(ui.chat)
                api(ui.home)
                api(ui.journal)
                api(ui.onboarding)
                api(ui.profile)
            }

            with(libs) {
                implementation(androidx.lifecycle.viewmodel)
                implementation(androidx.lifecycle.runtime.compose)
            }
        }

        desktopMain.dependencies {
            implementation(libs.kotlinx.coroutines.swing)
        }
    }
}

android {
    namespace = "com.fay"

    sourceSets["main"].apply {
        manifest.srcFile("src/androidMain/AndroidManifest.xml")
        res.srcDirs("src/androidMain/res")
        resources.srcDirs("src/commonMain/resources")
    }

    defaultConfig {
        applicationId = "com.fay.example"
        versionCode = 1
        versionName = "1.0"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    dependencies {
        with(libs) {
            debugImplementation(compose.uiTooling)
        }
    }
}

compose.desktop {
    application {
        mainClass = "com.fay.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.fay"
            packageVersion = "1.0.0"
        }
    }
}
