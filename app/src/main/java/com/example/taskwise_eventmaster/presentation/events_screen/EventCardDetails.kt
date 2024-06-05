package com.example.taskwise_eventmaster.presentation.events_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.taskwise_eventmaster.domain.model.Event
import java.time.format.DateTimeFormatter

@Composable
fun EventCardDetails(
    event: Event,
    onEvent: (EventsScreenEvent) -> Unit,
    onDismiss: () -> Unit,
) {
    Dialog(
        onDismissRequest = {
            onDismiss()
        }) {

        Column(modifier = Modifier.padding(15.dp)) {

            EventImage(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                modelStr = event.thumbnails.firstOrNull()?.thumbnailUrl
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .verticalScroll(rememberScrollState()),
            ) {

                Text(
                    text = event.name,
                    modifier = Modifier
                        .padding(2.dp)
                        .align(Alignment.CenterHorizontally),
                    color = Color.White,
                    fontSize = 40.sp,
                    lineHeight = 35.sp,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    modifier = Modifier
                        .padding(2.dp),
                    text = "Type of Event: ${event.type}",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold
                )


                val formatter = DateTimeFormatter.ofPattern("dd-MMMM-yyyy, HH:mm")

                Text(
                    text = "When: " + formatter.format(event.dateTimeUtc),
                    modifier = Modifier
                        .padding(1.dp),
                    fontWeight = FontWeight.Bold,
                    lineHeight = 30.sp,
                    color = Color.White,
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 30.sp
                )

                Text(
                    modifier = Modifier
                        .padding(2.dp),
                    text = "Location: ${event.address}, ${event.city}, ${event.country}",
                    fontSize = 26.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp),
                    contentAlignment = Alignment.CenterEnd
                ) {

                    Button(
                        onClick = {
                            onEvent(EventsScreenEvent.SaveEventInCalendar(event))
                            onDismiss()
                        },
                    ) {
                        Text(
                            text = "Add to Calendar",
                            fontSize = 20.sp
                        )
                    }
                }
            }
        }
    }
}