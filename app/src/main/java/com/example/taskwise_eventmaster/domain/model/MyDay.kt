package com.example.taskwise_eventmaster.domain.model

import androidx.compose.foundation.background
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import io.github.boguszpawlowski.composecalendar.day.DayState
import io.github.boguszpawlowski.composecalendar.selection.DynamicSelectionState
import io.github.boguszpawlowski.composecalendar.selection.EmptySelectionState
import io.github.boguszpawlowski.composecalendar.selection.SelectionState
import java.time.LocalDate

@Composable
fun MyDay(
    modifier: Modifier = Modifier,
    dayState: DayState<DynamicSelectionState>,
    tasksForTheDay: List<Task>
) {
    val specialDate = LocalDate.of(2024, 4, 1)

    if (dayState.date == specialDate) {
        Text(
            text = "${dayState.date.dayOfMonth}",
            Modifier.background(color = Color.Gray)
        )
    } else {
        Text(dayState.date.dayOfMonth.toString())
    }
}