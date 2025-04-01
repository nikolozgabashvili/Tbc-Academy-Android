package com.example.tbcacademyhomework.presentation.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tbcacademyhomework.R
import com.example.tbcacademyhomework.presentation.designSystem.AppButton
import com.example.tbcacademyhomework.presentation.designSystem.AppCheckBox
import com.example.tbcacademyhomework.presentation.designSystem.AppTextField

@Composable
fun LoginScreen(
    state: LoginScreenState,
    snackBarHostState: SnackbarHostState? = null,
    onAction: (LoginScreenAction) -> Unit,
    navigateToRegister: () -> Unit,
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(scrollState)
            .padding(20.dp)
    ) {
        AppTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.emailText,
            imeAction = ImeAction.Next,
            enabled = !state.isLoading,
            label = stringResource(R.string.login),
            onValueChange = {
                onAction(LoginScreenAction.OnEmailChanged(it))
            }
        )
        Spacer(modifier = Modifier.height(10.dp))
        AppTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.passwordText,
            enabled = !state.isLoading,
            imeAction = ImeAction.Done,
            label = stringResource(R.string.password),
            onValueChange = {
                onAction(LoginScreenAction.OnPasswordChanged(it))
            }
        )

        Spacer(modifier = Modifier.height(10.dp))
        AppCheckBox(
            enabled = !state.isLoading,
            checked = state.remember,
            onCheckBoxClick = { onAction(LoginScreenAction.OnRememberClicked) }
        )

        Spacer(modifier = Modifier.weight(1f))
        snackBarHostState?.let {
            SnackbarHost(hostState = snackBarHostState)
        }
        AppButton(
            enabled = state.isUserValid,
            loading = state.isLoading,
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.login),
            onclick = {
                onAction(LoginScreenAction.OnLogin)
            }
        )
        Spacer(modifier = Modifier.height(10.dp))
        AppButton(
            modifier = Modifier.fillMaxWidth(),
            loading = state.isLoading,
            text = stringResource(R.string.register),
            buttonColor = Color.White,
            textColor = Color.Black,
            border = BorderStroke(width = 0.5.dp, color = Color.Black),
            onclick = {
                navigateToRegister()
            }
        )

    }


}

@Preview
@Composable
private fun LoginScreenPrev() {
    MaterialTheme {
        LoginScreen(
            state = LoginScreenState(),
            onAction = {},
            snackBarHostState = null,
            navigateToRegister = {}
        )
    }
}