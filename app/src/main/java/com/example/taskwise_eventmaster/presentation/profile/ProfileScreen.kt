package com.example.taskwise_eventmaster.presentation.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.taskwise_eventmaster.DestinationStrings.HOME
import com.example.taskwise_eventmaster.DestinationStrings.SIGN_IN
import com.example.taskwise_eventmaster.presentation.sign_in.SignInScreenEvent
import com.example.taskwise_eventmaster.presentation.sign_in.SignInState
import com.example.taskwise_eventmaster.presentation.sign_in.UserData
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(
    state: ProfileScreenState,
    onEvent: (ProfileScreenEvent) -> Unit,
    navController: NavHostController,
    snackbarHostState: SnackbarHostState
) {
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        if (state.userData?.profilePictureUrl != null) {
            AsyncImage(
                model = state.userData.profilePictureUrl,
                contentDescription = "Profile picture",
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        if (state.userData?.username != null) {
            Text(
                text = state.userData.username,
                textAlign = TextAlign.Center,
                fontSize = 36.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        Button(onClick = {
            onEvent(ProfileScreenEvent.OnSignOut)

            coroutineScope.launch {
                snackbarHostState.showSnackbar(
                    message = "Signed out",
                    duration = SnackbarDuration.Short
                )
            }

            navController.popBackStack(SIGN_IN.destinationString, false)
        }) {
            Text(text = "Sign out!")
        }

        Spacer(modifier = Modifier.height(46.dp))

        Button(onClick = {
            coroutineScope.launch {
                snackbarHostState.showSnackbar(
                    message = "Back to ${HOME.screenName}",
                    duration = SnackbarDuration.Short
                )
            }

            navController.popBackStack(HOME.destinationString, false)

        }) {
            Text(
                text = "Home",
                fontSize = 25.sp
            )
        }
    }
}