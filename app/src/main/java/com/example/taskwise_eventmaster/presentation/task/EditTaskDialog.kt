package com.example.taskwise_eventmaster.presentation.task

import RatingBar
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
import com.example.taskwise_eventmaster.presentation.calendar.DateTimePicker
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun EditTaskDialog(
    task: Task,
    onSave: (Task) -> Unit,
    onDismiss: () -> Unit,
) {

    var isValidInputTitle by remember { mutableStateOf(true) }
    var isValidInputDescription by remember { mutableStateOf(true) }
    var isValidInputDateTime by remember { mutableStateOf(true) }

    var title by remember { mutableStateOf(task.title) }
    var estimationTime by remember { mutableStateOf(task.estimationTime) }
    var levelOfDifficulty by remember { mutableStateOf(task.levelOfDifficulty) }
    var description by remember { mutableStateOf(task.description) }

    Dialog(
        onDismissRequest = {
            onDismiss()
        }) {
        Column(
            modifier = Modifier
                .padding(15.dp)
        ) {

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

                val titleErrorMsg = when {
                    title.length > 40 -> "*Title length must be less than 40 characters*"
                    else -> "*No title entered*"
                }

                if (!isValidInputTitle) {
                    Text(
                        text = titleErrorMsg,
                        color = Color.Red,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                }

                TextField(
                    value = title,
                    onValueChange = {
                        title = it
                        isValidInputTitle = title.length in 1..40
                    },
                    placeholder = { Text(text = "Enter Title") }
                )

                val descriptionErrorMsg = "*Description length must be less than 150 characters*"

                if (!isValidInputDescription) {
                    Text(
                        text = descriptionErrorMsg,
                        color = Color.Red,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                }

                Text(
                    text = "Description:",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )

                TextField(
                    value = description,
                    onValueChange = {
                        description = it
                        isValidInputDescription = description.length <= 150
                    },
                    placeholder = { Text(text = "(optional)") }
                )

                Text(
                    text = "Level of Difficulty: 1-5",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )

                RatingBar(
                    currentRating = levelOfDifficulty,
                    onRatingChanged = { newRating ->
                        levelOfDifficulty = newRating
                    }
                )

                Text(
                    text = "Estimated Time: ",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )

                val previousDate = task.estimationTime
                val currentDateTime = LocalDateTime.now()

                isValidInputDateTime =
                    (estimationTime.dayOfMonth >= previousDate.dayOfMonth &&
                            estimationTime.month >= previousDate.month &&
                            estimationTime.year >= previousDate.year &&
                            estimationTime.hour >= previousDate.hour) ||

                            (estimationTime.dayOfMonth > previousDate.dayOfMonth &&
                                    estimationTime.month >= previousDate.month &&
                                    estimationTime.year >= previousDate.year) ||

                            (estimationTime.dayOfMonth >= currentDateTime.dayOfMonth &&
                                    estimationTime.month >= currentDateTime.month &&
                                    estimationTime.year >= currentDateTime.year)

                if (!isValidInputDateTime) {
                    Text(
                        text = "If you are editing a task some days after its creation, pick a new date/time from the future",
                        color = Color.Red,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                }

                DateTimePicker(onSetDateTime = { selectedDateTime ->
                    estimationTime = selectedDateTime
                })

                val formatter = DateTimeFormatter.ofPattern("dd-MMMM-yyyy, HH:mm")

                Text(
                    text = "Chosen data-time: " + formatter.format(estimationTime),
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
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
                            onSave(editedTask)
                            onDismiss()
                        },
                        enabled = isValidInputTitle &&
                                isValidInputDescription &&
                                isValidInputDateTime
                    ) {
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