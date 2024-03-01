package com.example.taskwise_eventmaster

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.taskwise_eventmaster.presentation.sign_in.SignInScreen
import com.example.taskwise_eventmaster.presentation.sign_in.SignInViewModel
import com.example.taskwise_eventmaster.ui.theme.TaskWise_EventMasterTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


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

                    NavHost(
                        navController = navController,
                        startDestination = "sign_in"
                    ) //host all of our different screens
                    {
                        composable("sign_in") {
                            val viewModel = hiltViewModel<SignInViewModel>()
                            val state = viewModel.state

                            //let's logon
                            SignInScreen(
                                state = state,
                                onEvent = viewModel::onEvent,
                                navController = navController,
                                snackbarHostState = snackbarHostState
                            )
                        }

                        /*composable("profile") {
                            ProfileScreen(
                                userData = googleAuthUiClient.getSignedInUser(),
                                onSignOut = {
                                    lifecycleScope.launch {
                                        googleAuthUiClient.signOut()
                                        coroutineScope.launch {
                                            snackbarHostState.showSnackbar(
                                                message = "Signed out",
                                                duration = SnackbarDuration.Short
                                            )
                                        }
                                        navController.popBackStack("sign_in", false)
                                    }
                                },
                                goToHomePage = {
                                    navController.popBackStack("home_page", false)
                                }
                            )
                        }*/

                        /*composable("home_page") {
                            HomePage(
                                userData = googleAuthUiClient.getSignedInUser(),

                                goToProfilePage = {
                                    lifecycleScope.launch {
                                        coroutineScope.launch {
                                            snackbarHostState.showSnackbar(
                                                message = "Profile page",
                                                duration = SnackbarDuration.Short
                                            )
                                        }
                                        navController.navigate("profile")
                                    }
                                },
                                goToTaskPage = {},
                                goToEventsPage = {},
                                goToGoalsPage = {},
                                goToPlanningViewPage = { navController.navigate("planning_view") },
                            )
                        }*/

                        /*composable("planning_view") {
                            PlanningView(
                                userData = googleAuthUiClient.getSignedInUser(),

                                goToProfilePage = {
                                    lifecycleScope.launch {
                                        coroutineScope.launch {
                                            snackbarHostState.showSnackbar(
                                                message = "Profile page",
                                                duration = SnackbarDuration.Short
                                            )
                                        }
                                        navController.navigate("profile")
                                    }
                                },

                                goToTaskPage = {
                                    lifecycleScope.launch {
                                        coroutineScope.launch {
                                            snackbarHostState.showSnackbar(
                                                message = "Back to home page",
                                                duration = SnackbarDuration.Short
                                            )
                                        }
                                        navController.popBackStack()
                                    }
                                }
                            )
                        }
*/

                    }
                    SnackbarHost(hostState = snackbarHostState)
                }
            }
        }
    }
}
