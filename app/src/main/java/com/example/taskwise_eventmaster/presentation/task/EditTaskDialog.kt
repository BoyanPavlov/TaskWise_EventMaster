package com.example.taskwise_eventmaster.presentation.task

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.taskwise_eventmaster.domain.model.Task
import java.time.LocalDateTime

@Composable
fun EditTaskDialog(
    task: Task,
    onEvent: (TaskEvent) -> Unit,
    onDismiss: () -> Unit
) {

    var title by remember { mutableStateOf(task.title) }
    var estimationTime by remember { mutableStateOf(task.estimationTime) }
    var levelOfDifficulty by remember { mutableStateOf(task.levelOfDifficulty) }
    var description by remember { mutableStateOf(task.description) }

    Dialog(
        onDismissRequest = {
            onDismiss()
        }) {
        Column(modifier = Modifier.padding(15.dp)) {

            Row(horizontalArrangement = Arrangement.Start) {

                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .border(1.dp, color = Color.White, shape = RectangleShape)
                        .background(color = Color.Black)
                        .size(70.dp)
                )

                Text(
                    modifier = Modifier
                        .padding(20.dp),
                    text = "Edit task",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp
                )
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .verticalScroll(rememberScrollState()),
            ) {

                Text(
                    text = "Title:",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )

                TextField(
                    value = title,
                    onValueChange = { title = it },
                    placeholder = { Text(text = "Enter Title") }
                )

                Text(
                    text = "Description:",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )

                TextField(
                    value = description,
                    onValueChange = { description = it },
                    placeholder = { Text(text = "(optional)") }
                )

                Text(
                    text = "Level of Difficulty: 1-5",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )

                TextField(
                    value = levelOfDifficulty.toString(),
                    onValueChange = { newValue ->
                        newValue.toIntOrNull()?.let { validInt ->
                            levelOfDifficulty = validInt
                        }
                    },
                    placeholder = { Text(text = "Level of Difficulty") }
                )

                Text(
                    text = "Estimated Time: ",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )


                TextField(
                    value = estimationTime.toString(),
                    onValueChange = {
                        try {
                            estimationTime = LocalDateTime.parse(it)
                        } catch (e: Exception) {
                            println("estimation time kaput")
                        }
                    },
                    placeholder = { Text(text = "yyyy-mm-ddThh:mm") }
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp),
                    contentAlignment = Alignment.CenterEnd
                ) {

                    Button(
                        onClick = {
                            val editedTask =
                                Task(
                                    title = title,
                                    estimationTime = estimationTime,
                                    levelOfDifficulty = levelOfDifficulty,
                                    description = description,
                                    id = task.id,
                                    checkedAsDone = task.checkedAsDone
                                )
                            onEvent(TaskEvent.EditTask(editedTask))
                            onDismiss()
                        }) {
                        Text(
                            text = "Save changes",
                            fontSize = 20.sp
                        )
                    }
                }
            }
        }
    }
}