package com.example.tbcacademyhomework.presentation.screen.register

import androidx.compose.foundation.ScrollState
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
import androidx.compose.material.icons.automirrored.rounded.ArrowBackIos
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tbcacademyhomework.R
import com.example.tbcacademyhomework.presentation.designSystem.AppButton
import com.example.tbcacademyhomework.presentation.designSystem.AppIcon
import com.example.tbcacademyhomework.presentation.designSystem.AppTextField
import com.example.tbcacademyhomework.presentation.designSystem.PasswordTextField
import com.example.tbcacademyhomework.presentation.theme.AppColor
import com.example.tbcacademyhomework.presentation.theme.AppTheme
import com.example.tbcacademyhomework.presentation.utils.CollectEvent
import com.example.tbcacademyhomework.presentation.utils.getValue

@Composable
fun RegisterScreenRoot(
    viewmodel: RegisterViewModel = hiltViewModel(),
    showError: suspend (String) -> Unit,
    navigateBack: (UserParams?) -> Unit
) {

    val context = LocalContext.current
    val scrollState = rememberScrollState()

    CollectEvent(viewmodel.event) {
        when (it) {
            is RegisterEvent.Error -> {
                val message = it.error.getValue(context)
                message?.let {
                    showError(it)
                }
            }

            is RegisterEvent.Success -> {
                navigateBack(it.params)


            }

            RegisterEvent.NavigateBack -> {
                navigateBack(null)
            }
        }
    }


    RegisterScreen(
        state = viewmodel.state,
        scrollState = scrollState,
        onAction = viewmodel::onAction,

        )

}


@Composable
private fun RegisterScreen(
    state: RegisterScreenState,
    scrollState: ScrollState,
    onAction: (RegisterScreenAction) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColor.canvas)
            .verticalScroll(scrollState)
            .padding(20.dp)
    ) {

        IconButton(
            onClick = { onAction(RegisterScreenAction.NavigateBack) },
            enabled = !state.isLoading
        ) {
            AppIcon(
                icon = Icons.AutoMirrored.Rounded.ArrowBackIos,
                onClick = { onAction(RegisterScreenAction.NavigateBack) })
        }

        AppTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.email,
            startIcon = Icons.Rounded.Email,
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Email,
            enabled = !state.isLoading,
            label = stringResource(R.string.email),
            onValueChange = {
                onAction(RegisterScreenAction.EnterEmail(it))
            }
        )
        Spacer(modifier = Modifier.height(10.dp))
        PasswordTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.password,
            startIcon = Icons.Rounded.Lock,
            enabled = !state.isLoading,
            imeAction = ImeAction.Next,
            label = stringResource(R.string.password),
            onValueChange = {
                onAction(RegisterScreenAction.EnterPassword(it))
            }
        )

        Spacer(modifier = Modifier.height(10.dp))
        PasswordTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.repeatPassword,
            startIcon = Icons.Rounded.Lock,
            enabled = !state.isLoading,
            imeAction = ImeAction.Done,
            label = stringResource(R.string.password),
            onValueChange = {
                onAction(RegisterScreenAction.EnterRepeatPassword(it))
            }
        )


        Spacer(modifier = Modifier.weight(1f))

        AppButton(
            enabled = state.isUserValid,
            loading = state.isLoading,
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.login),
            onclick = {
                onAction(RegisterScreenAction.Register)
            }
        )

    }


}

@Preview
@Composable
private fun LoginScreenPrev() {
    AppTheme {
        RegisterScreen(
            state = RegisterScreenState(),
            onAction = {},
            scrollState = rememberScrollState()
        )
    }
}