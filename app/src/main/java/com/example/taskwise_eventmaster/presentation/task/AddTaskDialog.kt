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
import androidx.compose.material.icons.filled.Add
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
fun AddTaskDialog(
    onEvent: (TaskEvent) -> Unit,
    onDismiss: () -> Unit,
) {
    var isValidInputTitle by remember { mutableStateOf(false) }
    var isValidInputDescription by remember { mutableStateOf(true) }
    var isValidInputLevelOfDifficulty by remember { mutableStateOf(false) }
    var isValidInputDateTime by remember { mutableStateOf(false) }

    var title by remember { mutableStateOf("") }
    var estimationTime by remember { mutableStateOf(LocalDateTime.now()) }
    var levelOfDifficulty by remember { mutableStateOf(0) }
    var description by remember { mutableStateOf("") }


    Dialog(
        onDismissRequest = {
            onDismiss()
        }) {
        Column(modifier = Modifier.padding(15.dp)) {

            Row(horizontalArrangement = Arrangement.Start) {

                Icon(
                    imageVector = Icons.Default.Add,
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
                    text = "Add task",
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

                if (!isValidInputDescription) {
                    Text(
                        text = "*Description length must be less than 150 characters*",
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

                if (!isValidInputLevelOfDifficulty) {
                    Text(
                        text = "*Please enter level of difficulty*",
                        color = Color.Red,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                }

                RatingBar(
                    currentRating = levelOfDifficulty,
                    onRatingChanged = { newRating ->
                        levelOfDifficulty = newRating
                        isValidInputLevelOfDifficulty = levelOfDifficulty in 1..5
                    }
                )

                Text(
                    text = "Estimated Time:",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )

                val timeNow = LocalDateTime.now()

                isValidInputDateTime =
                    (estimationTime.dayOfMonth >= timeNow.dayOfMonth &&
                            estimationTime.month >= timeNow.month &&
                            estimationTime.year >= timeNow.year &&
                            estimationTime.hour >= timeNow.hour) ||

                            (estimationTime.month > timeNow.month &&
                                    estimationTime.year >= timeNow.year) ||

                            (estimationTime.year > timeNow.year)

                if (!isValidInputDateTime) {
                    Text(
                        text = "Please pick time from the future",
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
                    text = formatter.format(estimationTime),
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
                            val task =
                                Task(
                                    title = title,
                                    estimationTime = estimationTime,
                                    levelOfDifficulty = levelOfDifficulty,
                                    description = description
                                )
                            onEvent(TaskEvent.SaveTask(task))
                            onDismiss()
                        },
                        enabled = isValidInputTitle && isValidInputDescription
                                && isValidInputLevelOfDifficulty && isValidInputDateTime
                    ) {
                        Text(
                            text = "Save",
                            fontSize = 20.sp
                        )
                    }
                }
            }
        }
    }
}