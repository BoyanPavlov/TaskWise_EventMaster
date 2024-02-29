package com.example.taskwise_eventmaster.presentation.controllers

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavHostController
import com.example.taskwise_eventmaster.presentation.sign_in.GoogleAuthUiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class NavigationController(
    var lifecycleScope: LifecycleCoroutineScope,
    var googleAuthUiClient: GoogleAuthUiClient,
    var snackbarHostState: SnackbarHostState,
    var coroutineScope: CoroutineScope,
    var navController: NavHostController
) {

    val onSignOut: () -> Unit = {
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
    }

    //=========================================================================
    private fun displaySnackbar(destination: String) {
        coroutineScope.launch {
            snackbarHostState.showSnackbar(
                message = "Welcome to " + destination + "!",
                duration = SnackbarDuration.Short
            )
        }
    }

    val goToProfilePage: () -> Unit = {
        lifecycleScope.launch {
            displaySnackbar("Profile page")
            navController.navigate("profile")
        }
    }

    //TODO
    val goToEventsPage: () -> Unit = {
        //displaySnackbar("Events page")
        //navController.navigate("events")
    }

    //TODO
    val goToGoalsPage: () -> Unit = {
        //displaySnackbar("Goals page")
        //navController.navigate("goals")
    }

    val goToPlanningViewPage: () -> Unit = {
        lifecycleScope.launch {
            displaySnackbar("Planning page")
            navController.navigate("planning_view")
        }
    }

    //TODO
    val goToTaskPage: () -> Unit = {
        //lifecycleScope.launch {
        // displaySnackbar("Task page")
        // navController.navigate("task_page")
        //}
    }

    val goToHomePage: () -> Unit = {
        lifecycleScope.launch {
            displaySnackbar("Home page")
            navController.popBackStack("home_page", false)
        }
    }

    val goBack: () -> Unit = {
        navController.popBackStack()
    }


}