package com.example.taskwise_eventmaster.presentation.events_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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

    Box(
        modifier = modifier.fillMaxSize(),
    ) {
        AsyncImage(
            model = event.thumbnailUrl,
            contentDescription = "Event picture",
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Column(modifier = modifier.align(alignment = Alignment.BottomCenter)) {

            Text(
                text = event.name,
                textAlign = TextAlign.Center,
                fontSize = 36.sp,
                fontWeight = FontWeight.SemiBold
            )

            val minutes = if (event.dateTime_Utc.minute < 10) {
                "0${event.dateTime_Utc.minute}"
            } else {
                event.dateTime_Utc.minute.toString()
            }

            Text(
                text = "Chosen Date-Time: ${event.dateTime_Utc.year}-${event.dateTime_Utc.month}-${event.dateTime_Utc.dayOfMonth}, ${event.dateTime_Utc.hour}:${minutes}",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )

            Text(
                text = "${event.address}, ${event.city}, ${event.country}",
                textAlign = TextAlign.Center,
                fontSize = 36.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}