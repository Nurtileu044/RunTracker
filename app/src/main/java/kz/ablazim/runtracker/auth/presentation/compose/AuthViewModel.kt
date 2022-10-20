package kz.ablazim.runtracker.auth.presentation.compose

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kz.ablazim.runtracker.auth.data.remote.AuthRepository

class AuthViewModel(private val authRepository: AuthRepository = AuthRepository()) : ViewModel() {

    val currentUser = authRepository.currentUser

    val hasUser: Boolean
        get() = authRepository.hasUser()

    var authUiState by mutableStateOf(AuthUiState())
        private set

    fun onUserNameChange(userName: String) {
        authUiState = authUiState.copy(userName = userName)
    }

    fun onPasswordChange(password: String) {
        authUiState = authUiState.copy(password = password)
    }

    fun onUserNameChangeSignUp(userName: String) {
        authUiState = authUiState.copy(userNameSignUp = userName)
    }

    fun onPasswordChangeSignUp(password: String) {
        authUiState = authUiState.copy(passwordSignUp = password)
    }

    fun onConfirmPasswordChange(password: String) {
        authUiState = authUiState.copy(confirmPasswordSignUp = password)
    }

    private fun validateLoginForm() = authUiState.userName.isBlank() || authUiState.password.isBlank()

    private fun validateSignUpForm() =
        authUiState.userNameSignUp.isBlank() && authUiState.passwordSignUp.isBlank() && authUiState.confirmPasswordSignUp.isBlank()

    fun createUser(context: Context) = viewModelScope.launch {
        try {
            if (!validateSignUpForm()) {
                throw IllegalArgumentException("email and password cannot be empty")
            }
            authUiState = authUiState.copy(isLoading = true)
            if (authUiState.passwordSignUp != authUiState.confirmPasswordSignUp) {
                throw IllegalArgumentException("Passwords do not match")
            }
            authUiState = authUiState.copy(signUpError = null)
            authRepository.createUser(
                email = authUiState.userNameSignUp,
                password = authUiState.passwordSignUp
            ) { isSuccessful ->
                authUiState = if (isSuccessful) {
                    Toast.makeText(context, "Success login", Toast.LENGTH_SHORT).show()
                    authUiState.copy(isSuccessfulLogin = true)
                } else {
                    Toast.makeText(context, "Failed login", Toast.LENGTH_SHORT).show()
                    authUiState.copy(isSuccessfulLogin = false)
                }
            }
        } catch (e: Exception) {
            authUiState = authUiState.copy(signUpError = e.localizedMessage)
            e.printStackTrace()
        } finally {
            authUiState = authUiState.copy(isLoading = false)
        }
    }

    fun loginUser(context: Context) = viewModelScope.launch {
        try {
            if (validateLoginForm()) {
                throw IllegalArgumentException("email and password cannot be empty")
            }
            authUiState = authUiState.copy(isLoading = true)
            authUiState = authUiState.copy(loginError = null)
            authRepository.login(
                email = authUiState.userName,
                password = authUiState.password
            ) { isSuccessful ->
                authUiState = if (isSuccessful) {
                    Toast.makeText(context, "Success login", Toast.LENGTH_SHORT).show()
                    authUiState.copy(isSuccessfulLogin = true)
                } else {
                    Toast.makeText(context, "Failed login", Toast.LENGTH_SHORT).show()
                    authUiState.copy(isSuccessfulLogin = false)
                }
            }
        } catch (e: Exception) {
            authUiState = authUiState.copy(loginError = e.localizedMessage)
            e.printStackTrace()
        } finally {
            authUiState = authUiState.copy(isLoading = false)
        }
    }
}

data class AuthUiState(
    val userName: String = "",
    val password: String = "",
    val userNameSignUp: String = "",
    val passwordSignUp: String = "",
    val confirmPasswordSignUp: String = "",
    val isLoading: Boolean = false,
    val isSuccessfulLogin: Boolean = false,
    val signUpError: String? = null,
    val loginError: String? = null
)