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
fun listOfCards(): List<ImageCard> {
    val tasksButtonPic = painterResource(id = R.drawable.tasks_icon)
    val planningViewButton = painterResource(id = R.drawable.planning_view_icon)
    val goalsButtonPic = painterResource(id = R.drawable.goals_icon)
    val eventsButtonPic = painterResource(id = R.drawable.events_icon)

    val taskCard = ImageCard(tasksButtonPic, "Task button page", "Tasks")
    val planningViewCard = ImageCard(planningViewButton, "Planning view page", "Planning view")
    val goalsCard = ImageCard(goalsButtonPic, "Goals button page", "Goals")
    val eventsCard = ImageCard(eventsButtonPic, "Events button page", "Events")

    return listOf(taskCard, planningViewCard, goalsCard, eventsCard)
}

@Composable
fun MenuPart(
    imageCards: List<ImageCard>,
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()

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
                ImageCardDisplayed(imageCard = imageCards[0])
                ImageCardDisplayed(imageCard = imageCards[1])
            }
            Row {
                ImageCardDisplayed(imageCard = imageCards[2])
                ImageCardDisplayed(imageCard = imageCards[3])
            }
        }

        val screens = listOf(TASK, GOAL, EVENTS, PLANNING_VIEW)


        for (i in screens.indices) {
            imageCards[i].onClickImage = {
                coroutineScope.launch {
                    snackbarHostState.showSnackbar(
                        message = "Welcome to ${screens[i].screenName}",
                        duration = SnackbarDuration.Short
                    )
                }
                navController.navigate(screens[i].destinationString)
            }
        }
    }
}

