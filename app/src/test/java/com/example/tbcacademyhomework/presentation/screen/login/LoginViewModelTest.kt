package com.example.tbcacademyhomework.presentation.screen.login

import app.cash.turbine.test
import com.example.tbcacademyhomework.domain.auth.models.AuthUser
import com.example.tbcacademyhomework.domain.auth.models.LoginResponseDomain
import com.example.tbcacademyhomework.domain.auth.usecase.LoginUseCase
import com.example.tbcacademyhomework.domain.auth.usecase.ValidateEmailUseCase
import com.example.tbcacademyhomework.domain.auth.usecase.ValidatePasswordUseCase
import com.example.tbcacademyhomework.domain.common.usecase.ClearDataUseCase
import com.example.tbcacademyhomework.domain.datastore.usecase.GetValueUseCase
import com.example.tbcacademyhomework.domain.utils.Resource
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelTest {

    private lateinit var viewModel: LoginViewModel

    private val loginUseCase: LoginUseCase = mockk()
    private val getValueUseCase: GetValueUseCase = mockk()
    private val validateEmailUseCase: ValidateEmailUseCase = mockk()
    private val validatePasswordUseCase: ValidatePasswordUseCase = mockk()
    private val clearDataUseCase: ClearDataUseCase = mockk()

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)

        coEvery { clearDataUseCase() } just Runs

        viewModel = LoginViewModel(
            loginUseCase,
            getValueUseCase,
            validateEmailUseCase,
            validatePasswordUseCase,
            clearDataUseCase
        )

        testDispatcher.scheduler.advanceUntilIdle()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `loginUser emits Success event when login is successful`() = runTest {

        val email = "test@example.com"
        val password = "password123"
        val authUser = AuthUser(email, password)

        coEvery { loginUseCase(authUser, any()) } returns flowOf(Resource.Success(
            LoginResponseDomain("token")
        ))

        viewModel.onAction(LoginScreenAction.OnEmailChanged(email))
        viewModel.onAction(LoginScreenAction.OnPasswordChanged(password))


        viewModel.event.test {
            viewModel.onAction(LoginScreenAction.OnLogin)

            assertEquals(LoginEvent.Success, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }
}
