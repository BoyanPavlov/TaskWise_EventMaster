package com.example.taskwise_eventmaster.presentation.sign_in

import android.content.Intent

sealed interface SignInScreenEvent {
    data object SignButtonClicked : SignInScreenEvent

    data class CompleteSignIn(val intent: Intent) : SignInScreenEvent

    data object OnSuccessfulSignIn : SignInScreenEvent

    data object ScreenOpened: SignInScreenEvent
}