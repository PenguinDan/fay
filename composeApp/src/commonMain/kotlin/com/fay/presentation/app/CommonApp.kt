package com.fay.presentation.app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.fay.core.ui.navigation.AppScreen
import com.slack.circuit.backstack.rememberSaveableBackStack
import com.slack.circuit.foundation.Circuit
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.NavigableCircuitContent
import com.slack.circuit.foundation.rememberCircuitNavigator
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject

typealias CommonAppCreator = (CommonApp.Configuration) -> CommonApp

@Inject
class CommonApp(
    @Assisted private val configuration: Configuration,
    private val circuit: Circuit,
) {
    data class Configuration(
        val onRootPopped: () -> Unit,
    )

    @Composable
    fun Screen(modifier: Modifier = Modifier) {
        val backStack = rememberSaveableBackStack(root = AppScreen.Login)
        val navigator = rememberCircuitNavigator(
            backStack,
            onRootPop = { configuration.onRootPopped() },
            enableBackHandler = true,
        )

        MaterialTheme {
            CircuitCompositionLocals(circuit) {
                Scaffold(
                    modifier = modifier.imePadding(),
                ) { contentPadding ->
                    NavigableCircuitContent(
                        navigator,
                        backStack,
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.background)
                            .padding(contentPadding),
                    )
                }
            }
        }
    }
}
