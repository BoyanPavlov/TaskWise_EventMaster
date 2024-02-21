package com.example.taskwise_eventmaster.presentation.home_page

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.github.boguszpawlowski.composecalendar.StaticCalendar


@Composable
fun CalendarPart(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .padding(top = 3.dp)
            .border(5.dp, color = Color.Black)
            .padding(7.dp)
            .background(color = Color.LightGray)
    ) {
        StaticCalendar()
    }
}
