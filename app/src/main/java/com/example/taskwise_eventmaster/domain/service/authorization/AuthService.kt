package com.example.taskwise_eventmaster.domain.service.authorization

import android.app.PendingIntent
import android.content.Intent
import com.example.taskwise_eventmaster.presentation.sign_in.SignInResult
import com.example.taskwise_eventmaster.presentation.sign_in.UserData


interface AuthService {
    suspend fun createSignInIntent() : PendingIntent?

    suspend fun signInWithIntent(intent: Intent): SignInResult

    suspend fun signOut()

    fun getSignedInUser():UserData?
}