package com.fay.ui.onboarding.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.fay.core.base.inject.UiScope
import com.fay.core.ui.navigation.AppScreen
import com.fay.core.ui.theme.spacing
import com.slack.circuit.codegen.annotations.CircuitInject
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@CircuitInject(AppScreen.Login::class, UiScope::class)
fun LoginUi(
    state: LoginPresenter.UiState,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.padding(horizontal = MaterialTheme.spacing.large),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.usernameInput,
                onValueChange = {
                    state.eventSink(LoginPresenter.Event.UsernameInputUpdated(it))
                },
                isError = state.isError,
                enabled = !state.isLoading,
                maxLines = 1,
                placeholder = { Text("Username") },
            )

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = MaterialTheme.spacing.small),
                value = state.passwordInput,
                onValueChange = {
                    state.eventSink(LoginPresenter.Event.PasswordInputUpdated(it))
                },
                isError = state.isError,
                enabled = !state.isLoading,
                maxLines = 1,
                placeholder = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
            )

            if (state.isError) {
                Text(
                    "Invalid username and password combination",
                    color = MaterialTheme.colorScheme.error,
                )
            }

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = MaterialTheme.spacing.medium),
                shape = MaterialTheme.shapes.medium,
                onClick = { state.eventSink(LoginPresenter.Event.LoginPressed) },
                enabled = state.isLoginButtonEnabled && !state.isLoading,
            ) {
                Text("Login")
            }
        }
    }
}

@Preview
@Composable
private fun LoginUi_Preview() {
    LoginUi(state = LoginPresenter.UiState())
}
