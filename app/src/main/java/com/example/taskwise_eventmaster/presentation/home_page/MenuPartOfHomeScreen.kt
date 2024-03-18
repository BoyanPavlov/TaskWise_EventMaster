package com.example.taskwise_eventmaster.presentation.home_page

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.taskwise_eventmaster.DestinationStrings.EVENTS
import com.example.taskwise_eventmaster.DestinationStrings.GOAL
import com.example.taskwise_eventmaster.DestinationStrings.PLANNING_VIEW
import com.example.taskwise_eventmaster.DestinationStrings.TASK
import com.example.taskwise_eventmaster.R
import kotlinx.coroutines.launch


@Composable
fun MenuPart(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()

    val tasksButtonPic = painterResource(id = R.drawable.tasks_icon)
    val planningViewButton = painterResource(id = R.drawable.planning_view_icon)
    val goalsButtonPic = painterResource(id = R.drawable.goals_icon)
    val eventsButtonPic = painterResource(id = R.drawable.events_icon)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .border(5.dp, color = Color.Black)
            .padding(9.dp),
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
        ) {
            Row {
                ImageCard(
                    title = TASK.screenName,
                    backgroundImage = tasksButtonPic,
                    onClickImage = {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar(
                                message = "Welcome to ${TASK.screenName}",
                                duration = SnackbarDuration.Short
                            )
                        }
                        navController.navigate(TASK.destinationString)
                    }
                )

                ImageCard(
                    title = PLANNING_VIEW.screenName,
                    backgroundImage = planningViewButton,
                    onClickImage = {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar(
                                message = "Welcome to ${PLANNING_VIEW.screenName}",
                                duration = SnackbarDuration.Short
                            )
                        }
                        navController.navigate(PLANNING_VIEW.destinationString)
                    }
                )

            }
            Row {
                ImageCard(
                    title = GOAL.screenName,
                    backgroundImage = goalsButtonPic,
                    onClickImage = {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar(
                                message = "Welcome to ${GOAL.screenName}",
                                duration = SnackbarDuration.Short
                            )
                        }
                        navController.navigate(GOAL.destinationString)
                    }
                )

                ImageCard(
                    title = EVENTS.screenName,
                    backgroundImage = eventsButtonPic,
                    onClickImage = {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar(
                                message = "Welcome to ${EVENTS.screenName}",
                                duration = SnackbarDuration.Short
                            )
                        }
                        navController.navigate(EVENTS.destinationString)
                    }
                )
            }
        }
    }
}

