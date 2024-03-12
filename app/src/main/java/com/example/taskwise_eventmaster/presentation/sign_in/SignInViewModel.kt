package com.example.taskwise_eventmaster.presentation.sign_in

import android.content.Intent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskwise_eventmaster.service.authorization.AuthService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authService: AuthService
) : ViewModel() {
    var state by mutableStateOf(SignInState())
        private set

    fun onEvent(event: SignInScreenEvent) =
        when (event) {
            is SignInScreenEvent.CompleteSignIn -> resultFromSignInWithIntent(event.intent)
            is SignInScreenEvent.OnSuccessfulSignIn -> resetState()
            is SignInScreenEvent.SignButtonClicked -> signIn()
        }


    private fun signIn() {
        viewModelScope.launch {
            val signInIntent = authService.createSignInIntent()
            state = state.copy(
                intentSender = signInIntent?.intentSender
            )
        }
    }

    private fun onSignInResult(result: SignInResult) {
        state = state.copy(
            isSignInSuccessful = result.data != null,
            signInErrorMessage = result.errorMessage
        )
    }

    private fun resultFromSignInWithIntent(intentData: Intent) {
        viewModelScope.launch {
            val signInResult = authService.signInWithIntent(intentData)

            onSignInResult(signInResult)
        }
    }

    private fun resetState() {
        state = SignInState()
    }
}