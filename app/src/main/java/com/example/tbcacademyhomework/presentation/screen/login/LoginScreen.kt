package com.example.tbcacademyhomework.presentation.screen.login

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tbcacademyhomework.R
import com.example.tbcacademyhomework.presentation.designSystem.AppButton
import com.example.tbcacademyhomework.presentation.designSystem.AppCheckBox
import com.example.tbcacademyhomework.presentation.designSystem.AppTextField
import com.example.tbcacademyhomework.presentation.designSystem.PasswordTextField
import com.example.tbcacademyhomework.presentation.theme.AppColor
import com.example.tbcacademyhomework.presentation.theme.AppTheme
import com.example.tbcacademyhomework.presentation.utils.CollectEvent
import com.example.tbcacademyhomework.presentation.utils.GenericString


@Composable
fun LoginScreenRoot(
    viewmodel: LoginViewModel = hiltViewModel(),
    userEmail: String,
    userPassword: String,
    showError: suspend (GenericString) -> Unit,
    navigateToRegister: () -> Unit,
    navigateToUsers: () -> Unit
) {


    LaunchedEffect(Unit) {
        viewmodel.onAction(LoginScreenAction.OnEmailChanged(userEmail))
        viewmodel.onAction(LoginScreenAction.OnPasswordChanged(userPassword))
    }

    CollectEvent(viewmodel.event) {
        when (it) {
            is LoginEvent.Error -> {
                showError(it.error)
            }

            LoginEvent.Success -> {
                navigateToUsers()
            }
        }
    }


    LoginScreen(
        state = viewmodel.state,
        onAction = viewmodel::onAction,
        navigateToRegister = navigateToRegister
    )

}


@Composable
private fun LoginScreen(
    state: LoginScreenState,
    onAction: (LoginScreenAction) -> Unit,
    navigateToRegister: () -> Unit,
) {

    if (!state.showScreen) return

    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColor.canvas)
            .verticalScroll(scrollState)
            .padding(20.dp)
    ) {
        AppTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.emailText,
            startIcon = Icons.Rounded.Email,
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next,
            enabled = !state.isLoading,
            label = stringResource(R.string.email),
            onValueChange = {
                onAction(LoginScreenAction.OnEmailChanged(it))
            }
        )
        Spacer(modifier = Modifier.height(10.dp))
        PasswordTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.passwordText,
            startIcon = Icons.Rounded.Lock,
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
            buttonColor = AppColor.secondary,
            textColor = AppColor.primary,
            border = BorderStroke(width = 0.5.dp, color = AppColor.primary),
            onclick = navigateToRegister
        )

    }


}

@Preview
@Composable
private fun LoginScreenPrev() {
    AppTheme {
        LoginScreen(
            state = LoginScreenState(),
            onAction = {},
            navigateToRegister = {}
        )
    }
}