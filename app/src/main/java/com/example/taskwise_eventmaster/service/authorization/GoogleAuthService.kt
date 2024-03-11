package com.example.taskwise_eventmaster.service.authorization

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.example.taskwise_eventmaster.R
import com.example.taskwise_eventmaster.presentation.sign_in.SignInResult
import com.example.taskwise_eventmaster.presentation.sign_in.UserData
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.firebase.Firebase
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.util.concurrent.CancellationException
import javax.inject.Inject

class GoogleAuthService @Inject constructor(
    @ApplicationContext private val context: Context,
) : AuthService {

    private val oneTapClient = Identity.getSignInClient(context)
    private val auth = Firebase.auth
    override suspend fun signOut() = withContext(Dispatchers.IO) {
        try {
            oneTapClient.signOut().await()
            auth.signOut()
        } catch (e: Exception) {
            if (e is CancellationException) throw e

            e.printStackTrace()
        }
    }

    override suspend fun createSignInIntent(): PendingIntent? = withContext(Dispatchers.IO) {
        val result = try {
            oneTapClient.beginSignIn(buildSignInRequest()).await()

        } catch (e: Exception) {
            if (e is CancellationException) throw e

            e.printStackTrace()

            null
        }
        return@withContext result?.pendingIntent
    }

    private fun buildSignInRequest(): BeginSignInRequest =
        BeginSignInRequest.Builder()
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)//Setting this to true indicates that your application expects to receive an ID token upon a successful sign-in.
                    .setFilterByAuthorizedAccounts(false)//false because we want to get all of our google acc, not just one
                    .setServerClientId(context.getString(R.string.web_client_id))
                    .build()
            )
            .setAutoSelectEnabled(true)
            .build()


    override suspend fun signInWithIntent(intent: Intent): SignInResult = withContext(Dispatchers.IO){
        val credential = oneTapClient.getSignInCredentialFromIntent(intent)
        val googleIdToken = credential.googleIdToken
        val googleCredentials = GoogleAuthProvider.getCredential(googleIdToken, null)

        return@withContext try {
            val user = auth
                .signInWithCredential(googleCredentials)
                .await()
                .user

            SignInResult(
                data = user?.run {
                    UserData(
                        userId = uid,
                        username = displayName,
                        profilePictureUrl = photoUrl?.toString()
                    )
                },
                errorMessage = null
            )
        } catch (e: Exception) {
            if (e is CancellationException) throw e

            e.printStackTrace()

            SignInResult(
                data = null,
                errorMessage = e.message
            )
        }
    }
}