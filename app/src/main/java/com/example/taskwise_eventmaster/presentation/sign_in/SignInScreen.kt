package com.example.taskwise_eventmaster.presentation.sign_in

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

//file for the first screen
@Composable
fun SignInScreen(
    state:SignInState,
    onSignInClick:() -> Unit
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = state.signInErrorMessage) {
        state.signInErrorMessage?.let { error ->
            Toast.makeText(
                context,
                error,
                Toast.LENGTH_LONG
            ).show()
        }
    }

//    val snackbarHostState = remember { SnackbarHostState() }
//    state.signInErrorMessage?.let{ error ->
//        LaunchedEffect(error) {
//            snackbarHostState.showSnackbar(
//                message = error,
//                duration = SnackbarDuration.Long
//            )
//        }
//    }

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        //SnackbarHost(hostState = snackbarHostState)
        Button(onClick = onSignInClick) {
            Text(text ="Sign in")
        }
    }
}