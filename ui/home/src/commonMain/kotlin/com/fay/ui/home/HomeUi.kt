package com.fay.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.fay.core.base.inject.UiScope
import com.fay.core.ui.navigation.AppScreen
import com.slack.circuit.backstack.rememberSaveableBackStack
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.foundation.NavigableCircuitContent
import com.slack.circuit.foundation.rememberCircuitNavigator

@Composable
@CircuitInject(AppScreen.Home::class, UiScope::class)
fun HomeUi(
    @Suppress("unused") state: HomeScreenState,
    modifier: Modifier = Modifier,
) {
    val backStack = rememberSaveableBackStack(root = AppScreen.Appointments)
    val navigator = rememberCircuitNavigator(
        backStack,
        onRootPop = {
            // TODO
        },
        enableBackHandler = true,
    )

    var selectedIndex by remember { mutableStateOf(0) }

    Scaffold(
        modifier = modifier,
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = selectedIndex == 0,
                    onClick = {
                        selectedIndex = 0
                        navigator.resetRoot(AppScreen.Appointments)
                    },
                    label = { Text("Appointments") },
                    icon = { }
                )
                NavigationBarItem(
                    selected = selectedIndex == 1,
                    onClick = {
                        selectedIndex = 1
                        navigator.resetRoot(AppScreen.Chat)
                    },
                    label = { Text("Chat") },
                    icon = { }
                )
                NavigationBarItem(
                    selected = selectedIndex == 2,
                    onClick = {
                        selectedIndex = 2
                        navigator.resetRoot(AppScreen.Journal)
                    },
                    label = { Text("Journal") },
                    icon = { }
                )
                NavigationBarItem(
                    selected = selectedIndex == 3,
                    onClick = {
                        selectedIndex = 3
                        navigator.resetRoot(AppScreen.Profile)
                    },
                    label = { Text("Profile") },
                    icon = { }
                )
            }
        }
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
