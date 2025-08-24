package com.fay.ui.journal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.fay.core.base.inject.UiScope
import com.fay.core.ui.navigation.AppScreen
import com.slack.circuit.codegen.annotations.CircuitInject

@Composable
@CircuitInject(AppScreen.Journal::class, UiScope::class)
fun JournalUi(
    state: JournalPresenter.UiState,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.background(Color.Yellow)) {
    }
}