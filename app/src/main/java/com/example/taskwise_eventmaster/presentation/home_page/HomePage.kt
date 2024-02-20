package com.example.taskwise_eventmaster.presentation.home_page

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.taskwise_eventmaster.R
import com.example.taskwise_eventmaster.presentation.sign_in.UserData
import io.github.boguszpawlowski.composecalendar.StaticCalendar

@Composable
fun ProfilePart(
    userData: UserData?,
    goToLogOnPage: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(bottom = 3.dp)
            .fillMaxWidth()
            .fillMaxHeight(0.2f)
            .border(5.dp, color = Color.Black)
            .padding(10.dp)
    )
    {

        var coverPhoto = painterResource(id = R.drawable.cover_photo_image)
        Image(
            painter = coverPhoto,
            contentDescription = null,
            contentScale = ContentScale.Crop,//used for scaling the image
            alpha = 0.3f // used for changing the opacity of the image
        )

        Column()
        {
            if (userData?.profilePictureUrl != null) {
                Box(modifier = modifier
                    .padding(1.dp)
                    .border(2.dp, color = Color.White, shape = CircleShape))
                {
                    AsyncImage(
                        model = userData.profilePictureUrl,
                        contentDescription = "Profile picture",
                        modifier = Modifier
                            .size(92.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                }
            }
            Spacer(modifier = Modifier.height(3.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                if (userData?.username != null) {
                    Box(
                        modifier = Modifier
                            .background(color = Color.LightGray)
                            .padding(1.dp)
                    ) {
                        Text(
                            text = "Welcome, ${userData.username}!",
                            textAlign = TextAlign.Center,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }

                Button(onClick = goToLogOnPage) {
                    //redirecting to logon page with log out button
                    Text(text = "Sign out")
                }
            }
        }
    }
}

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

@Composable
fun HomePage(
    userData: UserData?,
    goToLogOnPage: () -> Unit,
    goToTaskPage: () -> Unit,
    goToGoalsPage: () -> Unit,
    goToEventsPage: () -> Unit,
    goToPlanningViewPage: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            //.verticalScroll(rememberScrollState())
    ) {
        ProfilePart(userData = userData, goToLogOnPage = goToLogOnPage)

        CalendarPart()
    }
}