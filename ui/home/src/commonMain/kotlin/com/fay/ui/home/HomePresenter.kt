package com.fay.ui.home

import androidx.compose.runtime.Composable
import com.fay.core.base.inject.UiScope
import com.fay.core.ui.navigation.AppScreen
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject

data class HomeScreenState(
    val isLoading: Boolean,
) : CircuitUiState

@Inject
@CircuitInject(AppScreen.Home::class, UiScope::class)
class HomePresenter(
    @Suppress("unused") @Assisted private val navigator: Navigator,
) : Presenter<HomeScreenState> {
    @Composable
    override fun present(): HomeScreenState {
        return HomeScreenState(isLoading = true)
    }
}
