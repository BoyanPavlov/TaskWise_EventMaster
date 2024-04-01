package com.example.taskwise_eventmaster.presentation.task

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.taskwise_eventmaster.domain.model.Task

@Composable
fun TaskDialogForADayInCalendar(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    tasks: List<Task>
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Tasks:") },
        text = {
            Column(modifier=Modifier.verticalScroll(rememberScrollState())) {
                for (task in tasks) {
                    Text(task.title)
                }
            }
        },
        confirmButton = {
            Button(onClick = onDismiss) {
                Text("Close")
            }
        }
    )
}