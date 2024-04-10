package com.example.taskwise_eventmaster.presentation.home_page

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.taskwise_eventmaster.DestinationStrings
import com.example.taskwise_eventmaster.presentation.calendar.CalendarView
import com.example.taskwise_eventmaster.presentation.calendar.CalendarViewEvent
import com.example.taskwise_eventmaster.presentation.calendar.CalendarViewState
import kotlinx.coroutines.launch


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun HomeScreen(
    homeScreenState: HomeScreenState,
    calendarState: CalendarViewState,
    onCalendarEvent: (CalendarViewEvent) -> Unit,
    navController: NavHostController,
    snackbarHostState: SnackbarHostState
) {
    LaunchedEffect(key1 = Unit) {
        onCalendarEvent(CalendarViewEvent.LoadTasks)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val coroutineScope = rememberCoroutineScope()

        if (homeScreenState.userData == null) {
            coroutineScope.launch {
                snackbarHostState.showSnackbar(
                    message = "Problem with user data, please log in again",
                    duration = SnackbarDuration.Short
                )
            }

            navController.popBackStack(DestinationStrings.SIGN_IN.destinationString, false)

            return@Column
        }

        ProfilePart(
            userData = homeScreenState.userData,
            navController = navController,
            snackbarHostState = snackbarHostState
        )

        MenuPart(
            navController = navController,
            snackbarHostState = snackbarHostState
        )

        CalendarView(
            state = calendarState,
            navController = navController
        )
    }
}