package com.fay

import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import com.fay.inject.IOSApplicationComponent
import com.fay.inject.IOSUiComponent
import com.fay.presentation.app.CommonApp
import platform.UIKit.UIViewController

fun mainViewController(
    applicationComponent: IOSApplicationComponent,
): UIViewController {
    val iosUIComponent = (applicationComponent as IOSUiComponent.Factory).createIOSUiComponent()

    return ComposeUIViewController {
        val commonApp = remember {
            iosUIComponent.commonAppCreator(
                CommonApp.Configuration(onRootPopped = {}),
            )
        }

        commonApp.Screen()
    }
}
