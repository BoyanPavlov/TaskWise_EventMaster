package com.example.taskwise_eventmaster.presentation.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.taskwise_eventmaster.presentation.task.TaskEvent

@Composable
fun RadioButtons(
    sortType: SortType,
    onEvent: (TaskEvent) -> Unit
) {
    val radioButton by remember { mutableStateOf(sortType) }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxSize()
            .clickable { }
    ) {
        for (itemSelected in SortType.entries) {

            RadioButton(
                selected = sortType == itemSelected,
                onClick = { onEvent(TaskEvent.SortTasks(sortType)) }
            )

            Text(
                text = sortType.name,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .clickable { onEvent(TaskEvent.SortTasks(sortType)) }
            )
        }
    }
}