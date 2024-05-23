package com.example.taskwise_eventmaster.presentation.events_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.taskwise_eventmaster.domain.model.Event

@Composable
fun EventCard(
    modifier: Modifier = Modifier,
    event: Event,
    onEvent: (EventsScreenEvent) -> Unit,
) {
    var openedDetailedView by remember { mutableStateOf(false) }

    Row(
        modifier = modifier
            .clickable {
                openedDetailedView = true
            }
            .fillMaxSize()
            .padding(bottom = 7.dp, top = 7.dp)
            .background(color = Color.LightGray),
    ) {

        if (openedDetailedView) {
            EventCardDetails(
                event = event,
                onEvent = onEvent,
                onDismiss = { openedDetailedView = false },
            )
        }

        var model = ""

        if (event.thumbnails.isNotEmpty()) {
            model = event.thumbnails.first().thumbnailUrl
        }

        AsyncImage(
            model = model,
            contentDescription = "Event picture",
            modifier = Modifier
                .padding(10.dp)
                .size(140.dp)
                .clip(CircleShape)
                .align(Alignment.CenterVertically),
            contentScale = ContentScale.Crop
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

            val minutes = if (event.dateTime_Utc.minute < 10) {
                "0${event.dateTime_Utc.minute}"
            } else {
                event.dateTime_Utc.minute.toString()
            }

            Text(
                text = "${event.dateTime_Utc.year}-${event.dateTime_Utc.month}-${event.dateTime_Utc.dayOfMonth}, ${event.dateTime_Utc.hour}:${minutes}",
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