package com.example.taskwise_eventmaster.presentation.events_screen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.taskwise_eventmaster.DestinationStrings
import com.example.taskwise_eventmaster.presentation.utils.IndeterminateCircularIndicator
import kotlinx.coroutines.launch

@Composable
fun EventsScreen(
    modifier: Modifier = Modifier,
    state: EventsState,
    onEvent: (EventsScreenEvent) -> Unit,
    navController: NavHostController,
    snackbarHostState: SnackbarHostState
) {
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.15f)
                .border(3.dp, color = Color.Black)
                .padding(8.dp)
        )
        {
            Text(
                text = "Events",
                modifier = Modifier.align(Alignment.Center),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
            )


            Button(
                modifier = Modifier.align(Alignment.BottomEnd),
                onClick = {
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(
                            message = DestinationStrings.HOME.screenName,
                            duration = SnackbarDuration.Short
                        )
                    }
                    navController.popBackStack(DestinationStrings.HOME.destinationString, false)
                }) {
                Text(text = "Back Home")
            }
        }

        Box(modifier = Modifier.fillMaxSize()) {

            if (state.isLoading) {

                IndeterminateCircularIndicator(modifier = Modifier.align(Alignment.Center))

            } else {

                LazyColumn(modifier = Modifier.border(2.dp, color = Color.Black)) {
                    items(state.events) { event ->

                        EventCard(
                            event = event,
                            onEvent = onEvent,
                        )
                    }
                }
            }
        }
    }
}