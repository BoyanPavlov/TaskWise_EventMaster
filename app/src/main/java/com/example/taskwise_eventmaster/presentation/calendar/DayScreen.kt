package com.example.taskwise_eventmaster.presentation.calendar

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
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
import com.example.taskwise_eventmaster.DestinationStrings
import com.example.taskwise_eventmaster.R
import com.example.taskwise_eventmaster.domain.model.Task
import kotlinx.coroutines.launch
import java.time.LocalDate

@Composable
fun DayScreen(
    modifier: Modifier = Modifier,
    chosenDate: LocalDate?,
    state: CalendarViewState,
    onEvent: (CalendarViewEvent) -> Unit,
    navController: NavHostController,
    snackbarHostState: SnackbarHostState
) {
    if (chosenDate != null) {

        val coroutineScope = rememberCoroutineScope()

        val tasks = state.tasks.filter { task: Task ->
            task.estimationTime.dayOfMonth == chosenDate.dayOfMonth &&
                    task.estimationTime.month == chosenDate.month &&
                    task.estimationTime.year == chosenDate.year
        }

        Column {

            Box(
                modifier = Modifier
                    .border(3.dp, color = Color.Black, shape = RectangleShape)
                    .background(color = Color.LightGray)
                    .padding(12.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
            ) {

                Row {
                    Text(
                        text = "${chosenDate.dayOfMonth}",
                        fontSize = 35.sp
                    )
                    Text(
                        text = "${chosenDate.month}",
                        fontSize = 25.sp
                    )
                    Text(
                        text = "${chosenDate.year}",
                        fontSize = 20.sp
                    )
                }

                Button(
                    onClick = {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar(
                                message = "Back to ${DestinationStrings.HOME.screenName}",
                                duration = SnackbarDuration.Short
                            )
                        }

                        navController.popBackStack(DestinationStrings.HOME.destinationString, false)

                    },
                    modifier = Modifier.align(Alignment.CenterEnd)
                ) {
                    Text(
                        text = "Home",
                        fontSize = 25.sp
                    )
                }
            }

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


                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .verticalScroll(rememberScrollState())
                    ) {

                        (0..23).forEach { hour ->

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(5.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                tasks.forEach { task ->
                                    Canvas(modifier = Modifier
                                        .width(30.dp)
                                        .fillMaxHeight()
                                        .padding(8.dp), onDraw = {

                                        val lineHeight = size.height
                                        val circleRadius = 8.dp.toPx()
                                        val hourStep = lineHeight / 24


                                        val y = hourStep * hour
                                        drawCircle(
                                            color = if (task.estimationTime.hour == hour) Color.Black
                                            else Color.LightGray,
                                            radius = circleRadius,
                                            center = center.copy(y = y)
                                        )
                                    })
                                }

                                Text(
                                    text = "$hour:00",
                                    fontSize = 20.sp,
                                    modifier = Modifier.padding(5.dp)
                                )

                                Column(modifier = Modifier.fillMaxWidth()) {
                                    tasks.filter { it.estimationTime.hour == hour }
                                        .forEach { task ->
                                            Text(
                                                text = task.title,
                                                fontWeight = FontWeight.Bold,
                                                fontSize = 18.sp,
                                                modifier = Modifier.padding(vertical = 2.dp)
                                            )
                                        }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}