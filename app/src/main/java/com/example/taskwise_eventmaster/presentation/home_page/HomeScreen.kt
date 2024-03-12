package com.example.taskwise_eventmaster.presentation.home_page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController


@Composable
fun HomeScreen(
    state: HomeScreenState,
    navController: NavHostController,
    snackbarHostState: SnackbarHostState
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (state.userData == null) {
            //TODO print error message
            return@Column
        }
        ProfilePart(userData = state.userData, navController = navController, snackbarHostState)

        MenuPart(
            imageCards = listOfCards(),
            navController = navController,
            snackbarHostState = snackbarHostState
        )

        CalendarPart()
    }
}