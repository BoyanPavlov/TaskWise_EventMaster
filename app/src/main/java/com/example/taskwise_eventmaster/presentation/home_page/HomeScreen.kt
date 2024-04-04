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
import kotlinx.coroutines.launch


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun HomeScreen(
    state: HomeScreenState,
    onEvent: (HomeScreenEvent) -> Unit,
    navController: NavHostController,
    snackbarHostState: SnackbarHostState
) {
    LaunchedEffect(key1 = Unit) {
        onEvent(HomeScreenEvent.LoadTasks)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val coroutineScope = rememberCoroutineScope()

        if (state.userData == null) {
            coroutineScope.launch {
                snackbarHostState.showSnackbar(
                    message = "Problem with user data, please log in again",
                    duration = SnackbarDuration.Short
                )
            }

            navController.popBackStack(DestinationStrings.SIGN_IN.destinationString, false)

            return@Column
        }
        ProfilePart(userData = state.userData, navController = navController, snackbarHostState)

        MenuPart(
            navController = navController,
            snackbarHostState = snackbarHostState
        )

        CalendarPart(state = state)
    }
}