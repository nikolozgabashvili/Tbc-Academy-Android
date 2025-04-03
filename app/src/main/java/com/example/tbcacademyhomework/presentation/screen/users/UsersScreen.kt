package com.example.tbcacademyhomework.presentation.screen.users


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tbcacademyhomework.R
import com.example.tbcacademyhomework.presentation.designSystem.AppButton
import com.example.tbcacademyhomework.presentation.screen.users.component.UserItem
import com.example.tbcacademyhomework.presentation.theme.AppTheme

@Composable
fun UsersScreenRoot(
    viewmodel: UsersViewModel = hiltViewModel(),
    navigateToProfile: () -> Unit
) {
    UsersScreen(
        state = viewmodel.state,
        onAction = viewmodel::onAction,
        navigateToProfile = navigateToProfile

    )


}


@Composable
private fun UsersScreen(
    state: UsersScreenState,
    onAction: (UserScreenAction) -> Unit,
    navigateToProfile: () -> Unit
) {

    val lazyState = rememberLazyListState()

    Column(modifier = Modifier.padding(horizontal = 20.dp)) {

        AppButton(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.profile),
            onclick = navigateToProfile
        )
        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn(state = lazyState, verticalArrangement = Arrangement.spacedBy(10.dp)) {

            items(state.users) {
                UserItem(modifier = Modifier, userUi = it)

            }
            item {
                val endReached = state.pagingFinished
                val isLoading = state.loading

                LaunchedEffect(Unit) {
                    if (!endReached && !isLoading) {
                        onAction(UserScreenAction.OnFetchRequest)
                    }
                }

                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .wrapContentWidth(Alignment.CenterHorizontally)
                    )
                }
            }

        }
    }


}

@Preview(showBackground = true)
@Composable
private fun UsersPrev() {
    AppTheme {
        UsersScreen(
            state = UsersScreenState(),
            onAction = {},
            navigateToProfile = {}
        )
    }
}