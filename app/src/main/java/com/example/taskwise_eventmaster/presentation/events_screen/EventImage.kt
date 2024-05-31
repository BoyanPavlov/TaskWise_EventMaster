package com.example.taskwise_eventmaster.presentation.events_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.taskwise_eventmaster.R


@Composable
fun EventImage(modifier: Modifier = Modifier, modelStr: String?) {
    if (modelStr.isNullOrEmpty()) {

        Image(
            painter = painterResource(id = R.drawable.notfound),
            contentDescription = "Event picture",
            modifier = Modifier
                .padding(10.dp)
                .size(140.dp)
                .clip(CircleShape)
                .then(modifier),
            contentScale = ContentScale.Crop
        )

    } else {

        AsyncImage(
            model = modelStr,
            contentDescription = "Event picture",
            modifier = Modifier
                .padding(10.dp)
                .size(140.dp)
                .clip(CircleShape)
                .then(modifier),
            contentScale = ContentScale.Crop
        )
    }

}
