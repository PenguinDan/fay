package com.fay

import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.fay.inject.DesktopApplicationComponent
import com.fay.inject.DesktopUiComponent
import com.fay.inject.create
import com.fay.presentation.app.CommonApp

fun main() {
    val applicationComponent = DesktopApplicationComponent::class.create()
    val desktopUiComponent = (applicationComponent as DesktopUiComponent.Factory).createDesktopUiComponent()

    return application {
        val commonApp = remember {
            desktopUiComponent.commonAppCreator(
                CommonApp.Configuration(onRootPopped = { exitApplication() }),
            )
        }

        Window(onCloseRequest = ::exitApplication, title = "Fay") {
            commonApp.Screen()
        }
    }
}
