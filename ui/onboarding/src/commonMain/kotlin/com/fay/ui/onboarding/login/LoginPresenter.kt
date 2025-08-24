package com.fay.ui.onboarding.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.fay.core.authentication.AuthManager
import com.fay.core.base.inject.UiScope
import com.fay.core.ui.circuit.rememberRetainedCoroutineScope
import com.fay.core.ui.navigation.AppScreen
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import kotlinx.coroutines.launch
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject

@Inject
@CircuitInject(AppScreen.Login::class, UiScope::class)
class LoginPresenter(
    @Assisted private val navigator: Navigator,
    private val authManager: AuthManager,
) : Presenter<LoginPresenter.UiState> {

    data class UiState(
        val usernameInput: String = "",
        val passwordInput: String = "",
        val isLoginButtonEnabled: Boolean = false,
        val isLoading: Boolean = false,
        val isError: Boolean = false,
        val eventSink: (Event) -> Unit = {},
    ) : CircuitUiState

    sealed class Event : CircuitUiEvent {
        data class UsernameInputUpdated(val newInput: String) : Event()
        data class PasswordInputUpdated(val newInput: String) : Event()
        data object LoginPressed : Event()
    }

    @Composable
    override fun present(): UiState {
        var usernameInput by remember { mutableStateOf("") }
        var passwordInput by remember { mutableStateOf("") }
        var isLoading by remember { mutableStateOf(false) }
        val isLoginButtonEnabled = usernameInput.isNotBlank() && passwordInput.isNotBlank() && !isLoading
        var isError by remember { mutableStateOf(false) }
        val coroutineScope = rememberRetainedCoroutineScope()

        return UiState(
            usernameInput = usernameInput,
            passwordInput = passwordInput,
            isLoginButtonEnabled = isLoginButtonEnabled,
            isError = isError,
            isLoading = isLoading,
        ) { event ->
            when (event) {
                is Event.UsernameInputUpdated -> usernameInput = event.newInput.trim()
                is Event.PasswordInputUpdated -> passwordInput = event.newInput.trim()
                is Event.LoginPressed -> {
                    coroutineScope.launch {
                        isLoading = true
                        isError = !authManager.login(usernameInput, passwordInput)
                        isLoading = false

                        if (!isError) {
                            navigator.goTo(AppScreen.Home)
                        }
                    }
                }
            }
        }
    }
}
