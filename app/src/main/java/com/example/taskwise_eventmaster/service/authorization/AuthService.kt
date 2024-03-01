package com.example.taskwise_eventmaster.service.authorization

import android.app.PendingIntent
import android.content.Intent
import com.example.taskwise_eventmaster.presentation.sign_in.SignInResult


interface AuthService {
    suspend fun createSignInIntent() : PendingIntent?
    suspend fun signInWithIntent(intent: Intent): SignInResult
    suspend fun signOut()
}