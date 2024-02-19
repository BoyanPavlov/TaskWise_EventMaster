package com.example.taskwise_eventmaster.presentation.sign_in

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SignInViewModel : ViewModel(){
    private val _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()

    fun onSignInResult(result: SignInResult) {
        _state.update {
            it.copy(
                isSignInSuccessful = result.data != null,
                signInErrorMessage = result.errorMessage
            )
        }
    }
    fun resetState() // function used when we go back to the login screen - to log out the user automatically
    {
        _state.update {
            SignInState()
        }
    }
}