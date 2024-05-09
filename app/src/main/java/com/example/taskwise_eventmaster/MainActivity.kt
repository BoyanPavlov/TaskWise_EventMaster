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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.taskwise_eventmaster.DestinationStringArguments.CHOSEN_DATE
import com.example.taskwise_eventmaster.DestinationStrings.DAY_SCREEN
import com.example.taskwise_eventmaster.DestinationStrings.EVENTS
import com.example.taskwise_eventmaster.DestinationStrings.GOAL
import com.example.taskwise_eventmaster.DestinationStrings.HOME
import com.example.taskwise_eventmaster.DestinationStrings.PLANNING_VIEW
import com.example.taskwise_eventmaster.DestinationStrings.PROFILE
import com.example.taskwise_eventmaster.DestinationStrings.SIGN_IN
import com.example.taskwise_eventmaster.DestinationStrings.TASK
import com.example.taskwise_eventmaster.presentation.calendar.CalendarViewModel
import com.example.taskwise_eventmaster.presentation.day.DayScreen
import com.example.taskwise_eventmaster.presentation.day.DayViewModel
import com.example.taskwise_eventmaster.presentation.home_page.HomeScreen
import com.example.taskwise_eventmaster.presentation.home_page.HomeScreenViewModel
import com.example.taskwise_eventmaster.presentation.profile.ProfileScreen
import com.example.taskwise_eventmaster.presentation.profile.ProfileScreenViewModel
import com.example.taskwise_eventmaster.presentation.sign_in.SignInScreen
import com.example.taskwise_eventmaster.presentation.sign_in.SignInViewModel
import com.example.taskwise_eventmaster.presentation.task.TaskScreen
import com.example.taskwise_eventmaster.presentation.task.TaskViewModel
import com.example.taskwise_eventmaster.ui.theme.TaskWise_EventMasterTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.datetime.toJavaLocalDate
import kotlinx.datetime.toLocalDate

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

                            val homeScreenViewModel = hiltViewModel<HomeScreenViewModel>()
                            val calendarViewModel = hiltViewModel<CalendarViewModel>()

                            HomeScreen(
                                homeScreenState = homeScreenViewModel.state,
                                calendarState = calendarViewModel.state,
                                onCalendarEvent = calendarViewModel::onEvent,
                                navController = navController,
                                snackbarHostState = snackbarHostState
                            )
                        }

                        composable(TASK.destinationString) {

                            val viewModel = hiltViewModel<TaskViewModel>()
                            val state = viewModel.state

                            TaskScreen(
                                state = state,
                                onEvent = viewModel::onEvent,
                                navController = navController,
                                snackbarHostState = snackbarHostState
                            )
                        }

                        composable(
                            "${DAY_SCREEN.destinationString}/{${CHOSEN_DATE}}",
                            arguments = listOf(navArgument(CHOSEN_DATE) {
                                type = NavType.StringType
                            })
                        ) { backStackEntry ->
                            val chosenDate =
                                backStackEntry.arguments?.getString(CHOSEN_DATE)
                                    ?.toLocalDate()
                                    ?.toJavaLocalDate()

                            if (chosenDate == null) {
                                navController.popBackStack()
                                return@composable
                            }

                            val viewModel = hiltViewModel<DayViewModel>()
                            val state = viewModel.state

                            DayScreen(
                                state = state,
                                onEvent = viewModel::onEvent,
                                chosenDate = chosenDate,
                                navController = navController,
                                snackbarHostState = snackbarHostState
                            )

                        }

                        composable(PLANNING_VIEW.destinationString) {
                            //val viewModel = hiltViewModel<PlanningViewModel>()
                            //val state = viewModel.state

                            /*PlanningView(
                                state = state,

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
                            )*/
                        }


                        composable(GOAL.destinationString) {
                            /*val viewModel = hiltViewModel<GoalViewModel>()
                            val state = viewModel.state

                            GoalScreen(
                                state = state,
                                onEvent = viewModel::onEvent,
                                navController = navController,
                                snackbarHostState = snackbarHostState
                            )
                            */
                        }

                        composable(EVENTS.destinationString) {

                            /*val viewModel = hiltViewModel<EventsViewModel>()
                            val state = viewModel.state

                            EventsScreen(
                                state = state,
                                onEvent = viewModel::onEvent,
                                navController = navController,
                                snackbarHostState = snackbarHostState
                            )*/
                        }

                    }
                    SnackbarHost(hostState = snackbarHostState)
                }
            }
        }
    }
}
