package com.fay.core.ui.navigation

import com.fay.core.base.kotlin.CommonParcelize
import com.slack.circuit.runtime.screen.Screen

sealed class AppScreen: Screen {
    @CommonParcelize
    data object Login : AppScreen()

    @CommonParcelize
    data object Home : AppScreen()

    @CommonParcelize
    data object Appointments : AppScreen()

    @CommonParcelize
    data object Chat : AppScreen()

    @CommonParcelize
    data object Journal : AppScreen()

    @CommonParcelize
    data object Profile : AppScreen()
}
