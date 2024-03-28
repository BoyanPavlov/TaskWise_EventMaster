package com.example.taskwise_eventmaster.presentation.home_page

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.taskwise_eventmaster.DestinationStrings.PROFILE
import com.example.taskwise_eventmaster.R
import com.example.taskwise_eventmaster.presentation.sign_in.UserData
import kotlinx.coroutines.launch

@Composable
fun ProfilePart(
    userData: UserData?,
    navController: NavController,
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = modifier
            .padding(bottom = 3.dp)
            .fillMaxWidth()
            .height(170.dp)
            .border(5.dp, color = Color.Black)
            .padding(8.dp)
    )
    {

        val coverPhoto = painterResource(id = R.drawable.cover_photo_image)
        Image(
            painter = coverPhoto,
            contentDescription = null,
            contentScale = ContentScale.Crop,//used for scaling the image
            alpha = 0.3f // used for changing the opacity of the image
        )

        Column() {
            if (userData?.profilePictureUrl != null) {
                Box(
                    modifier = modifier
                        .padding(1.dp)
                        .border(2.dp, color = Color.White, shape = CircleShape)
                ) {
                    AsyncImage(
                        model = userData.profilePictureUrl,
                        contentDescription = "Profile picture",
                        modifier = Modifier
                            .size(100.dp)
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

                Button(onClick = {
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(
                            message = PROFILE.screenName,
                            duration = SnackbarDuration.Short
                        )
                    }
                    navController.navigate(PROFILE.destinationString)
                }) {
                    Text(text = "Profile")
                }
            }
        }
    }
}