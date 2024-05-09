package com.example.taskwise_eventmaster.presentation.calendar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.clock.ClockDialog
import com.maxkeppeler.sheets.clock.models.ClockConfig
import com.maxkeppeler.sheets.clock.models.ClockSelection
import kotlinx.datetime.toJavaLocalTime
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateTimePicker(
    onSetDateTime: (LocalDateTime) -> Unit
) {

    val calendarState = rememberSheetState()
    var chosenDate = remember { mutableStateOf(LocalDate.now()) }
    val chosenTime = remember { mutableStateOf(LocalTime.now()) }

    CalendarDialog(
        state = calendarState,
        config = CalendarConfig(
            monthSelection = true,
            yearSelection = true
        ),
        selection = CalendarSelection.Date { date ->
            chosenDate.value = date
            onSetDateTime(LocalDateTime.of(chosenDate.value, chosenTime.value))
        }
    )

    val clockState = rememberSheetState()
    ClockDialog(
        state = clockState,
        config = ClockConfig(
            defaultTime = LocalTime.now(),
            is24HourFormat = true
        ),
        selection = ClockSelection.HoursMinutes { hours, minutes ->
            chosenTime.value = kotlinx.datetime.LocalTime(hours, minutes).toJavaLocalTime()
            onSetDateTime(LocalDateTime.of(chosenDate.value, chosenTime.value))
        }
    )

    Row(
        modifier = Modifier
            .fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(onClick = {
            calendarState.show()
        }) {
            Text(text = "Date picker")
        }

        Button(onClick = {
            clockState.show()
        }) {
            Text(text = "Time picker")
        }
    }

}