package com.example.taskwise_eventmaster.presentation.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.taskwise_eventmaster.presentation.task.TaskEvent

@Composable
fun RadioButtons(
    modifier: Modifier = Modifier,
    sortType: SortType,
    onEvent: (TaskEvent) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.padding(2.dp)
    ) {
        for (itemSelected in SortType.entries) {

            Row(horizontalArrangement = Arrangement.Start) {

                RadioButton(
                    selected = sortType == itemSelected,
                    onClick = {
                        onEvent(TaskEvent.SortTasks(itemSelected))
                    }
                )

                Text(
                    text = itemSelected.name,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .clickable { onEvent(TaskEvent.SortTasks(itemSelected)) }
                )
            }
        }
    }
}