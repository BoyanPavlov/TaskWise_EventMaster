package com.example.taskwise_eventmaster.presentation.day

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.taskwise_eventmaster.DestinationStrings.DAY_SCREEN
import com.example.taskwise_eventmaster.domain.model.Task
import java.time.LocalDate

@Composable
fun DayCard(
    modifier: Modifier = Modifier,
    isCurrentDay: Boolean,
    date: LocalDate,
    tasksForTheDay: List<Task>,
    navController: NavHostController,
) {
    val isBusyDay = !tasksForTheDay.isEmpty()
    val currentDayColor = Color.Blue

    Card(
        modifier = modifier
            .aspectRatio(1f)
            .padding(2.dp)
            .clickable {
                navController.navigate("${DAY_SCREEN.destinationString}/${date}")
            },
        border = if (isCurrentDay) BorderStroke(1.dp, currentDayColor) else null,
    ) {
        Box(
            modifier = Modifier
                .padding(10.dp)
                .align(Alignment.CenterHorizontally)
                .fillMaxSize(),
        ) {
            Text(
                text = date.dayOfMonth.toString()
            )

            if (isBusyDay) {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .background(color = Color.Red, CircleShape)
                        .align(Alignment.BottomEnd)
                        .padding(4.dp)
                )
            }
        }
    }
}