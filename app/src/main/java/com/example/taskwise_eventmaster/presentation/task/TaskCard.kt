package com.example.taskwise_eventmaster.presentation.task

import RatingBar
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskwise_eventmaster.domain.model.Task


@Composable
fun TaskCard(
    modifier: Modifier = Modifier,
    onEvent: (TaskEvent) -> Unit,
    task: Task,
) {
    var isEditingTask by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .border(width = 2.dp, color = Color.Black, shape = RectangleShape)
            .then(modifier)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clickable {
                    onEvent(TaskEvent.MarkTaskAsDone(task))
                }
                .padding(top = 6.dp, end = 6.dp)
                .fillMaxSize(),
            horizontalArrangement = Arrangement.Start
        ) {

            Checkbox(
                checked = task.checkedAsDone,
                onCheckedChange = { onEvent(TaskEvent.MarkTaskAsDone(task)) }
            )

            Text(
                text = task.title,
                fontWeight = FontWeight.Bold
            )

        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 10.dp, end = 10.dp)
        ) {

            IconButton(
                modifier = Modifier
                    .align(alignment = Alignment.CenterVertically),
                onClick = {
                    isEditingTask = true
                }) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit task"
                )
            }

            if (isEditingTask) {
                EditTaskDialog(
                    task = task,
                    onSave = { TaskEvent.EditTask(it) },
                    onDismiss = { isEditingTask = false },
                )
            }

            val estimationTime = task.estimationTime

            val minutes = if (estimationTime.minute < 10) {
                "0${estimationTime.minute}"
            } else {
                estimationTime.minute.toString()
            }

            Text(
                text = "${estimationTime.year}-${estimationTime.month}-${estimationTime.dayOfMonth}, ${estimationTime.hour}:$minutes",
                modifier = Modifier
                    .align(alignment = Alignment.CenterVertically)
                    .padding(10.dp)
            )

            IconButton(
                modifier = Modifier
                    .align(alignment = Alignment.CenterVertically),
                onClick = {
                    onEvent(TaskEvent.DeleteTask(task))
                }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete task"
                )
            }

        }


        if (task.description.isNotEmpty()) {
            Row(
                modifier = Modifier
                    .background(color = Color.LightGray)
                    .padding(15.dp)
                    .fillMaxSize()
                    .then(modifier)
            ) {
                Text(
                    text = "Description: ",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )
                Text(text = task.description)
            }
        }

        Spacer(modifier = Modifier.width(1.dp))

        Row(modifier.align(Alignment.CenterHorizontally)) {
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .alpha(3f)
                    .padding(5.dp)
            )
            {
                Box(modifier = Modifier.align(Alignment.CenterEnd))
                {
                    RatingBar(
                        currentRating = task.levelOfDifficulty,
                        onRatingChanged = { newRating ->
                            onEvent(TaskEvent.ChangeLevelOfDifficulty(task, newRating))
                        }
                    )
                }
            }
        }
    }
}

