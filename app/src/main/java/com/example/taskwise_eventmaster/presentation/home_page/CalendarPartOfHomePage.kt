package com.example.taskwise_eventmaster.presentation.home_page

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
import com.example.taskwise_eventmaster.R
import com.example.taskwise_eventmaster.presentation.utils.MyDay
import io.github.boguszpawlowski.composecalendar.StaticCalendar

@Composable
fun CalendarPart(
    modifier: Modifier = Modifier,
    state: HomeScreenState
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
            MyDay(
                dayState = dayState,
                tasks = state.tasks
            )
        })
    }
}
// list of days  if day is -- make it red/black;
// On click on colored day -- show dialog;