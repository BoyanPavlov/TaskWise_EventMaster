package com.example.taskwise_eventmaster

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.taskwise_eventmaster.presentation.home_page.HomePage
import com.example.taskwise_eventmaster.presentation.controllers.NavigationController
import com.example.taskwise_eventmaster.presentation.planning_page.PlanningView
import com.example.taskwise_eventmaster.presentation.profile.ProfileScreen
import com.example.taskwise_eventmaster.presentation.sign_in.GoogleAuthUiClient
import com.example.taskwise_eventmaster.presentation.sign_in.SignInScreen
import com.example.taskwise_eventmaster.presentation.sign_in.SignInViewModel
import com.example.taskwise_eventmaster.ui.theme.TaskWise_EventMasterTheme
import com.google.android.gms.auth.api.identity.Identity
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext),
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskWise_EventMasterTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val snackbarHostState = remember { SnackbarHostState() }
                    val coroutineScope = rememberCoroutineScope()

                    val navigationController = NavigationController(
                        navController = navController,
                        snackbarHostState = snackbarHostState,
                        coroutineScope = coroutineScope,
                        googleAuthUiClient = googleAuthUiClient,
                        lifecycleScope = lifecycleScope
                    )

                    NavHost(
                        navController = navController,
                        startDestination = "sign_in"
                    ) //host all of our different screens
                    {
                        composable("sign_in") {
                            val viewModel = viewModel<SignInViewModel>()
                            val state by viewModel.state.collectAsState()

                            LaunchedEffect(key1 = Unit) {
                                if (googleAuthUiClient.getSignedInUser() != null) {
                                    navController.navigate("home_page")
                                    //navController.navigate("planning_view")
                                }
                            }

                            //launcher - collecting states here
                            val launcher = rememberLauncherForActivityResult(
                                contract = ActivityResultContracts.StartIntentSenderForResult(),
                                onResult = { result ->
                                    if (result.resultCode == RESULT_OK) {
                                        lifecycleScope.launch {
                                            val intentData = result.data ?: return@launch
                                            val signInResult =
                                                googleAuthUiClient.signInWithIntent(intentData)
                                            viewModel.onSignInResult(signInResult)
                                        }
                                    }
                                }
                            )


                            LaunchedEffect(key1 = state.isSignInSuccessful) {
                                if (state.isSignInSuccessful) {
                                    coroutineScope.launch {
                                        snackbarHostState.showSnackbar(
                                            message = "Signed in successfully",
                                            duration = SnackbarDuration.Short
                                        )
                                    }

                                    navController.navigate("home_page")
                                    //navController.navigate("planning_view")
                                    viewModel.resetState()
                                }
                            }

                            //let's logon
                            SignInScreen(
                                state = state,
                                onSignInClick = {
                                    lifecycleScope.launch {
                                        val signInIntentSender =
                                            googleAuthUiClient.signIn() ?: return@launch
                                        launcher.launch(
                                            IntentSenderRequest.Builder(signInIntentSender).build()
                                        )
                                    }
                                }
                            )
                        }

                        composable("profile") {
                            ProfileScreen(
                                userData = googleAuthUiClient.getSignedInUser(),
                                navigationController
                            )
                        }

                        composable("home_page") {
                            HomePage(
                                userData = googleAuthUiClient.getSignedInUser(),
                                navigationController
                            )
                        }

                        composable("planning_view") {
                            PlanningView(
                                userData = googleAuthUiClient.getSignedInUser(),
                                navigationController
                            )
                        }

                        composable("task_page") {

                        }
                    }
                    SnackbarHost(hostState = snackbarHostState)
                }
            }
        }
    }
}
