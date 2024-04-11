package com.example.taskwise_eventmaster.presentation.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.taskwise_eventmaster.DestinationStrings
import kotlinx.coroutines.launch
import java.time.LocalDate

@Composable
fun DayHeading(
    modifier: Modifier=Modifier,
    chosenDate: LocalDate,
    navController: NavHostController,
    snackbarHostState: SnackbarHostState
) {

    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = modifier
            .border(3.dp, color = Color.Black, shape = RectangleShape)
            .background(color = Color.LightGray)
            .padding(12.dp)
            .fillMaxWidth()
    ) {

        Row {
            Text(
                text = "${chosenDate.dayOfMonth}",
                fontSize = 35.sp
            )
            Text(
                text = "${chosenDate.month}",
                fontSize = 25.sp
            )
            Text(
                text = "${chosenDate.year}",
                fontSize = 20.sp
            )
        }

        Button(
            onClick = {
                coroutineScope.launch {
                    snackbarHostState.showSnackbar(
                        message = "Back to ${DestinationStrings.HOME.screenName}",
                        duration = SnackbarDuration.Short
                    )
                }

                navController.popBackStack(DestinationStrings.HOME.destinationString, false)

            },
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            Text(
                text = "Home",
                fontSize = 25.sp
            )
        }
    }
}