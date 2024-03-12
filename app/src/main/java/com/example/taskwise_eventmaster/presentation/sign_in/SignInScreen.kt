package com.example.taskwise_eventmaster.presentation.sign_in

import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.taskwise_eventmaster.DestinationStrings.HOME
import kotlinx.coroutines.launch

@Composable
fun SignInScreen(
    state: SignInState,
    onEvent: (SignInScreenEvent) -> Unit,
    navController: NavHostController,
    snackbarHostState: SnackbarHostState
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()


    LaunchedEffect(key1 = Unit) {
        if (state.isUserAlreadySignedIn) {
            navController.navigate(HOME.destinationString)
        }
    }

    LaunchedEffect(key1 = state.isSignInSuccessful) {
        if (state.isSignInSuccessful) {
            coroutineScope.launch {
                snackbarHostState.showSnackbar(
                    message = "Signed in successfully",
                    duration = SnackbarDuration.Short
                )
            }

            navController.navigate(HOME.destinationString)
            onEvent(SignInScreenEvent.OnSuccessfulSignIn)
        }
    }

    LaunchedEffect(key1 = state.signInErrorMessage) {
        state.signInErrorMessage?.let { error ->
            Toast.makeText(
                context,
                error,
                Toast.LENGTH_LONG
            ).show()
        }
    }


    //launcher - collecting states here
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult(),
        onResult = { result ->
            if (result.resultCode == ComponentActivity.RESULT_OK) {
                coroutineScope.launch {
                    val intentData = result.data ?: return@launch

                    onEvent(SignInScreenEvent.CompleteSignIn(intentData))
                }
            }
        }
    )

    if (state.intentSender != null) {
        launcher.launch(IntentSenderRequest.Builder(state.intentSender).build())
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = {
            onEvent(SignInScreenEvent.SignButtonClicked)

        }) {
            Text(text = "Sign in")
        }
    }
}