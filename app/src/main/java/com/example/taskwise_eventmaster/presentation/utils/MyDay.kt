package com.example.taskwise_eventmaster.presentation.utils

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.example.taskwise_eventmaster.domain.model.Task
import io.github.boguszpawlowski.composecalendar.day.DayState
import io.github.boguszpawlowski.composecalendar.day.DefaultDay
import io.github.boguszpawlowski.composecalendar.selection.EmptySelectionState

@Composable
fun MyDay(
    modifier: Modifier = Modifier,
    dayState: DayState<EmptySelectionState>,
    tasks: List<Task>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        DefaultDay(state = dayState)

        for (task in tasks) {

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
        }
    }
}