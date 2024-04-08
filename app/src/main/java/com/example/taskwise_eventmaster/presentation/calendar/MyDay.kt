package com.example.taskwise_eventmaster.presentation.calendar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.taskwise_eventmaster.domain.model.Task
import io.github.boguszpawlowski.composecalendar.day.DayState
import io.github.boguszpawlowski.composecalendar.selection.EmptySelectionState

@Composable
fun MyDay(
    modifier: Modifier = Modifier,
    dayState: DayState<EmptySelectionState>,
    tasks: List<Task>,
    navController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {


        DayCard(
            state = dayState,
            navController = navController,
            tasksForTheDay = tasks
        )

        //DefaultDay(state = dayState)

        /*for (task in tasks) {

            if (dayState.date == task.estimationTime.toLocalDate()) {
                Row {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = null,
                    )

                    Text(
                        modifier = Modifier
                            .align(alignment = Alignment.CenterVertically)
                            .padding(1.dp),
                        text = task.title,
                        fontStyle = FontStyle.Italic
                    )
                }
            }
        }*/
    }
}