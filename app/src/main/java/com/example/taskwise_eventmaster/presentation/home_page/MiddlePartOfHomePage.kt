package com.example.taskwise_eventmaster.presentation.home_page

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.taskwise_eventmaster.R
import com.example.taskwise_eventmaster.presentation.controllers.NavigationController


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
    navigationController: NavigationController,
    imageCards: List<ImageCard>,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .border(5.dp, color = Color.Black)
            .padding(9.dp),
    ) {
        //taskCard, planningViewCard, goalsCard, eventsCard
        imageCards[0].onClickImage = navigationController.goToTaskPage
        imageCards[1].onClickImage = navigationController.goToPlanningViewPage
        imageCards[2].onClickImage = navigationController.goToGoalsPage
        imageCards[3].onClickImage = navigationController.goToEventsPage

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
    }
}

