package com.example.taskwise_eventmaster.presentation.calendar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.taskwise_eventmaster.R
import com.example.taskwise_eventmaster.presentation.calendar.day.DayCard
import io.github.boguszpawlowski.composecalendar.StaticCalendar

@Composable
fun CalendarView(
    modifier: Modifier = Modifier,
    state: CalendarViewState,
    navController: NavHostController,
) {
    Box(
        modifier = modifier
            .padding(top = 3.dp)
            .border(5.dp, color = Color.Black)
            .padding(7.dp)
            .background(color = Color.LightGray)
    ) {
        val coverPhoto = painterResource(id = R.drawable.calendar_cover_photo)
        Image(
            painter = coverPhoto,
            contentDescription = null,
            contentScale = ContentScale.Crop, //used for scaling the image
            alpha = 0.3f // used for changing the opacity of the image
        )


        StaticCalendar(dayContent = { dayState ->

            val tasksForTheDay = state.tasks.filter { task ->
                dayState.date.dayOfMonth == task.estimationTime.dayOfMonth &&
                        dayState.date.month == task.estimationTime.month &&
                        dayState.date.year == task.estimationTime.year
            }

            DayCard(
                isCurrentDay = dayState.isCurrentDay,
                date = dayState.date,
                navController = navController,
                tasksForTheDay = tasksForTheDay,
            )
        })
    }
}
// list of days  if day is -- make it red/black;
// On click on colored day -- show dialog;