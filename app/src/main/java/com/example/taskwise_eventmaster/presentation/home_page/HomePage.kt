package com.example.taskwise_eventmaster.presentation.home_page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.taskwise_eventmaster.presentation.sign_in.UserData


@Composable
fun HomePage(
    userData: UserData?,
    goToProfilePage: () -> Unit,
    goToTaskPage: () -> Unit,
    goToGoalsPage: () -> Unit,
    goToEventsPage: () -> Unit,
    goToPlanningViewPage: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            //.verticalScroll(rememberScrollState())
    ) {
        ProfilePart(userData = userData, goToProfilePage = goToProfilePage)

        MenuPart(
            goToTaskPage =  goToTaskPage ,
            goToGoalsPage = goToGoalsPage ,
            goToEventsPage = goToEventsPage,
            goToPlanningViewPage = goToPlanningViewPage,
            imageCards = listOfCards()
        )

        CalendarPart()
    }
}