package com.fay.ui.journal

import androidx.compose.runtime.Composable
import com.fay.core.base.inject.UiScope
import com.fay.core.ui.navigation.AppScreen
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject

@Inject
@CircuitInject(AppScreen.Journal::class, UiScope::class)
class JournalPresenter(
    @Assisted private val navigator: Navigator,
) : Presenter<JournalPresenter.UiState> {
    data class UiState(
        val testValue: Int = 0,
    ) : CircuitUiState

    @Composable
    override fun present(): UiState {
        return UiState()
    }
}