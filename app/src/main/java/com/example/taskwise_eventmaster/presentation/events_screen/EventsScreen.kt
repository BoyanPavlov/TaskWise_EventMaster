package com.example.taskwise_eventmaster.presentation.events_screen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
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
import com.example.taskwise_eventmaster.presentation.utils.PullToRefreshLazyColumn
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventsScreen(
    modifier: Modifier = Modifier,
    state: EventsState,
    onEvent: (EventsScreenEvent) -> Unit,
    navController: NavHostController,
    snackbarHostState: SnackbarHostState
) {
    val coroutineScope = rememberCoroutineScope()
    val pullToRefreshState = rememberPullToRefreshState()

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.85f)
                .align(Alignment.BottomCenter)
        ) {

            if (state.isLoading) {

                IndeterminateCircularIndicator(modifier = Modifier.align(Alignment.Center))

            } else {

                PullToRefreshLazyColumn(
                    state = pullToRefreshState,
                    modifier = Modifier.border(2.dp, color = Color.Black),
                    items = state.events,
                    content = { event ->
                        EventCard(
                            event = event,
                            onEvent = onEvent,
                        )
                    },
                    isRefreshing = state.isRefreshing,
                    onRefresh = {
                        onEvent(EventsScreenEvent.ReloadEvents)
                    }
                )
            }
        }

        PullToRefreshContainer(
            state = pullToRefreshState,
            modifier = Modifier
                .align(Alignment.TopCenter),
        )


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.15f)
                .border(3.dp, color = Color.Black)
                .padding(8.dp)
                .align(Alignment.TopCenter)
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
    }
}

