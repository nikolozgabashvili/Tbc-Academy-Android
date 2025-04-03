package com.example.tbcacademyhomework.presentation.screen.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tbcacademyhomework.R
import com.example.tbcacademyhomework.presentation.designSystem.AppButton
import com.example.tbcacademyhomework.presentation.theme.AppTheme
import com.example.tbcacademyhomework.presentation.utils.CollectEvent

@Composable
fun ProfileScreenRoot(
    viewmodel: ProfileViewModel = hiltViewModel(),
    navigateToLogin: () -> Unit
) {

    CollectEvent(viewmodel.event) {
        when (it) {
            ProfileScreenEvent.Success -> navigateToLogin()
        }
    }


    ProfileScreen(
        state = viewmodel.state,
        onAction = viewmodel::onAction,
    )

}


@Composable
private fun ProfileScreen(
    state: ProfileScreenState,
    onAction: (ProfileScreenAction) -> Unit,
) {


    Column(modifier = Modifier.padding(horizontal = 20.dp)) {
        state.email?.let {
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = state.email
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        AppButton(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.log_out),
            onclick = {
                onAction(ProfileScreenAction.OnLogout)
            }
        )

    }
}

@Preview(showBackground = true)
@Composable
private fun ProfilePrev() {
    AppTheme {
        ProfileScreen(
            state = ProfileScreenState(),
            onAction = {}
        )
    }
}