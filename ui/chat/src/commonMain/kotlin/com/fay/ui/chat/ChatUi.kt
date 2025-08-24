package com.fay.ui.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.fay.core.base.inject.UiScope
import com.fay.core.ui.navigation.AppScreen
import com.slack.circuit.codegen.annotations.CircuitInject

@Composable
@CircuitInject(AppScreen.Chat::class, UiScope::class)
fun ChatUi(
    state: ChatPresenter.UiState,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.background(Color.Gray)) {

    }
}