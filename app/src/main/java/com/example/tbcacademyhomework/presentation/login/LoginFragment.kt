package com.example.tbcacademyhomework.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupPositionProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.fragment.findNavController
import com.example.tbcacademyhomework.presentation.ext.observeEvent
import com.example.tbcacademyhomework.presentation.utils.getValue
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val loginViewModel: LoginViewModel by viewModels()
    private val navController by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MaterialTheme {
                    val snackBarHostState = remember { SnackbarHostState() }

                    val context = LocalContext.current
                    val scope = rememberCoroutineScope()
                    val state by loginViewModel.state.collectAsStateWithLifecycle()

                    observeEvent(loginViewModel.event) { event ->
                        when (event) {
                            is LoginEvent.Error -> {
                                event.error.getValue(context)?.let {
                                    scope.launch {
                                        snackBarHostState.showSnackbar(message = it)
                                    }
                                }
                            }

                            LoginEvent.Success -> {
                                navController.navigate(LoginFragmentDirections.actionLoginFragmentToUsersFragment())
                            }
                        }
                    }

                    Column {
                        LoginScreen(
                            state,
                            snackBarHostState,
                            navigateToRegister = { navController.navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment()) },
                            onAction = loginViewModel::onAction
                        )


                    }



                }


            }
        }

    }

}