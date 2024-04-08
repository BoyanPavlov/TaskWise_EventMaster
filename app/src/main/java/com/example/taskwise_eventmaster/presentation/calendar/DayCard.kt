package com.example.taskwise_eventmaster.presentation.calendar

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.taskwise_eventmaster.DestinationStrings.DAY_SCREEN
import com.example.taskwise_eventmaster.domain.model.Task
import io.github.boguszpawlowski.composecalendar.day.DayState
import io.github.boguszpawlowski.composecalendar.selection.SelectionState

@Composable
public fun <T : SelectionState> DayCard(
    modifier: Modifier = Modifier,
    tasksForTheDay: List<Task>,
    state: DayState<T>,
    navController: NavHostController,
) {
    val isBusyDay = tasksForTheDay.isEmpty()
    val date = state.date
    val selectionState = state.selectionState

    val currentDayColor = Color.Blue

    Card(
        modifier = modifier
            .aspectRatio(1f)
            .padding(2.dp)
            .background(color = Color.LightGray)
            .clickable { navController.navigate(DAY_SCREEN.destinationString) },
        border = if (state.isCurrentDay) BorderStroke(1.dp, currentDayColor) else null,
    ) {
        Box(
            modifier = Modifier
                .padding(4.dp)
                .clickable {
                    selectionState.onDateSelected(date)
                },
            contentAlignment = Alignment.Center,
        ) {
            if (isBusyDay) {
                Text(
                    text = date.dayOfMonth.toString(),
                    modifier = Modifier
                        .border(width = 2.dp, Color.Black, shape = CircleShape)
                        .background(color = Color.White)
                )
            } else {
                Text(
                    text = date.dayOfMonth.toString()
                )
            }
        }
    }
}