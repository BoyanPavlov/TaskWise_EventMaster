package com.example.taskwise_eventmaster.presentation.sign_in

import android.content.IntentSender

data class SignInState(
    val isSignInSuccessful: Boolean = false,
    val signInErrorMessage: String? = null,

    val isUserAlreadySignedIn: Boolean = false,
    val intentSender: IntentSender? = null
)
