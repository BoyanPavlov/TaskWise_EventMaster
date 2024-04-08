package com.example.taskwise_eventmaster.presentation.calendar

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskwise_eventmaster.domain.model.Task

@Composable
fun DayScreen(
    modifier: Modifier = Modifier,
    tasks: List<Task>,
    //onEvent: (HomeScreenEvent) -> Unit
) {
    Row(modifier = modifier.fillMaxSize()) {

        Canvas(modifier = Modifier
            .width(30.dp)
            .fillMaxHeight()
            .padding(8.dp), onDraw = {
            val lineHeight = size.height
            val circleRadius = 8.dp.toPx()
            val hourStep = lineHeight / 24

            // Draw the line
            drawLine(
                color = Color.Black,
                start = center.copy(y = 0f),
                end = center.copy(y = lineHeight),
                strokeWidth = 2.dp.toPx()
            )

            // Draw circles for each task
            tasks.forEach { task ->
                val y = hourStep * task.estimationTime.hour
                drawCircle(
                    color = Color.Black,
                    radius = circleRadius,
                    center = center.copy(y = y)
                )
            }
        })

        Column(modifier = Modifier.weight(1f)) {
            (0..23).forEach { hour ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "$hour:00",
                        fontSize = 10.sp,
                        modifier = Modifier.padding(5.dp)
                    )
                    Column(modifier = Modifier.fillMaxWidth()) {
                        tasks.filter { it.estimationTime.hour == hour }.forEach { task ->
                            Text(
                                text = task.title,
                                fontSize = 16.sp,
                                modifier = Modifier.padding(vertical = 2.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}