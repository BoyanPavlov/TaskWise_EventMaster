package com.example.taskwise_eventmaster.presentation.events_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskwise_eventmaster.domain.model.Event
import java.time.format.DateTimeFormatter

@Composable
fun EventCard(
    modifier: Modifier = Modifier,
    event: Event,
    onEvent: (EventsScreenEvent) -> Unit,
) {
    var isOpenedDetailedView by remember { mutableStateOf(false) }

    Row(
        modifier = modifier
            .clickable {
                isOpenedDetailedView = true
            }
            .fillMaxSize()
            .padding(bottom = 7.dp, top = 7.dp)
            .background(color = Color.LightGray),
    ) {

        if (isOpenedDetailedView) {
            EventCardDetails(
                event = event,
                onEvent = onEvent,
                onDismiss = { isOpenedDetailedView = false },
            )
        }

        EventImage(
            modifier = Modifier.align(Alignment.CenterVertically),
            modelStr = event.thumbnails.firstOrNull()?.thumbnailUrl
        )

        Column(
            modifier = Modifier
                .padding(10.dp)
        ) {

            Text(
                modifier = Modifier
                    .padding(1.dp),
                text = event.name,
                lineHeight = 28.sp,
                fontSize = 30.sp,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Bold
            )

            val formatter = DateTimeFormatter.ofPattern("dd-MMMM-yyyy, HH:mm")

            Text(
                text = formatter.format(event.dateTimeUtc),
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif,
                fontSize = 20.sp
            )

            Text(
                text = "${event.address}, ${event.city}, ${event.country}",
                fontSize = 26.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}