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
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.taskwise_eventmaster.DestinationStrings.HOME
import com.example.taskwise_eventmaster.DestinationStrings.PROFILE
import com.example.taskwise_eventmaster.DestinationStrings.SIGN_IN
import com.example.taskwise_eventmaster.presentation.home_page.HomeScreen
import com.example.taskwise_eventmaster.presentation.home_page.HomeScreenViewModel
import com.example.taskwise_eventmaster.presentation.profile.ProfileScreen
import com.example.taskwise_eventmaster.presentation.profile.ProfileScreenViewModel
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

                    NavHost(
                        navController = navController,
                        startDestination = SIGN_IN.destinationString
                    ) //host all of our different screens
                    {
                        composable(SIGN_IN.destinationString) {
                            val viewModel = hiltViewModel<SignInViewModel>()
                            val state = viewModel.state

                            SignInScreen(
                                state = state,
                                onEvent = viewModel::onEvent,
                                navController = navController,
                                snackbarHostState = snackbarHostState
                            )
                        }

                        composable(PROFILE.destinationString) {

                            val viewModel = hiltViewModel<ProfileScreenViewModel>()
                            val state = viewModel.state

                            ProfileScreen(
                                state = state,
                                onEvent = viewModel::onEvent,
                                navController = navController,
                                snackbarHostState = snackbarHostState
                            )
                        }

                        composable(HOME.destinationString) {

                            val viewModel = hiltViewModel<HomeScreenViewModel>()
                            val state = viewModel.state

                            HomeScreen(
                                state = state,
                                navController = navController,
                                snackbarHostState = snackbarHostState
                            )
                        }

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
