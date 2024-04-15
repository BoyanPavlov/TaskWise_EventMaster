package com.example.taskwise_eventmaster.presentation.day

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.taskwise_eventmaster.R
import com.example.taskwise_eventmaster.domain.model.Task
import com.example.taskwise_eventmaster.presentation.task.EditTaskDialog
import java.time.LocalDate

@Composable
fun DayScreen(
    modifier: Modifier = Modifier,
    chosenDate: LocalDate,
    state: DayState,
    onEvent: (DayEvent) -> Unit,
    navController: NavHostController,
    snackbarHostState: SnackbarHostState
) {

    Column {

        DayHeading(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            chosenDate = chosenDate,
            navController = navController,
            snackbarHostState = snackbarHostState
        )

        Box {

            val coverPhoto = painterResource(id = R.drawable.smiled_hedgehog)

            Image(
                painter = coverPhoto,
                contentDescription = null,
                contentScale = ContentScale.Crop,//used for scaling the image
                alpha = 0.3f // used for changing the opacity of the image
            )

            Row(
                modifier = modifier
                    .fillMaxSize()
                    .border(3.dp, color = Color.Black, shape = RectangleShape)
            ) {

                // Draw the line
                Canvas(modifier = Modifier
                    .width(30.dp)
                    .fillMaxHeight()
                    .padding(8.dp), onDraw = {
                    val lineHeight = size.height

                    drawLine(
                        color = Color.Black,
                        start = center.copy(y = 0f),
                        end = center.copy(y = lineHeight),
                        strokeWidth = 2.dp.toPx()
                    )
                })

                var editingTask by remember { mutableStateOf<Task?>(null) }

                LazyColumn {

                    items((0..23).toList()) { hour ->

                        val isBusyHour = state.tasksForTheDay.find { task -> task.estimationTime.hour == hour } != null

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Canvas(modifier = Modifier
                                .width(30.dp)
                                .fillMaxHeight()
                                .padding(8.dp), onDraw = {

                                val lineHeight = size.height
                                val circleRadius = 8.dp.toPx()
                                val hourStep = lineHeight / 24


                                val y = hourStep * hour
                                drawCircle(
                                    color = if (isBusyHour) Color.Black else Color.LightGray,
                                    radius = circleRadius,
                                    center = center.copy(y = y)
                                )
                            })

                            Text(
                                text = "$hour:00",
                                fontSize = 20.sp,
                                modifier = Modifier.padding(5.dp)
                            )

                            Column(modifier = Modifier.fillMaxWidth()) {
                                state.tasksForTheDay.filter { it.estimationTime.hour == hour }
                                    .forEach { task ->
                                        Text(
                                            modifier = Modifier
                                                .padding(vertical = 2.dp)
                                                .clickable {
                                                    editingTask = task
                                                },
                                            text = task.title,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 18.sp,
                                        )
                                    }
                            }
                        }
                    }
                }

                if (editingTask != null) {
                    EditTaskDialog(
                        task = editingTask!!,
                        onSave = { onEvent(DayEvent.EditTask(it)) },
                        onDismiss = { editingTask = null },
                    )
                }
            }
        }
    }

}