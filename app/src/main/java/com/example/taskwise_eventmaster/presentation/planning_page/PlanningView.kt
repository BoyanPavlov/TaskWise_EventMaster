package com.example.taskwise_eventmaster.presentation.planning_page

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskwise_eventmaster.presentation.goals.Goals
import com.example.taskwise_eventmaster.presentation.controllers.NavigationController
import com.example.taskwise_eventmaster.presentation.tasks.Tasks
import com.example.taskwise_eventmaster.presentation.sign_in.UserData

@Composable
fun PlanningView(
    userData: UserData?,
    navigationController: NavigationController,
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //TODO - import materials for home and back button and create TopPart
        //TODO create two LazyColumns for Tasks and Goals

        Box(//title part
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.15f)
                .border(width = 5.dp, color = Color.Black, shape = RectangleShape)
                .padding(10.dp)

        ) {
            Row(modifier = Modifier.align(Alignment.TopCenter)) {
                Text(
                    text = "Planning view",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(modifier = Modifier.align(Alignment.BottomEnd)) {
                Button(onClick = navigationController.goToHomePage) {
                    Text(text = "Home")
                }
            }
        }

        Box(//task part
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f)
                .border(width = 5.dp, color = Color.Black, shape = RectangleShape)
                .padding(10.dp)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Row() {
                    Text(
                        text = "Tasks",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))

                Tasks()
                /*LazyColumn{

                }*/
            }
        }

        Box(//goals part
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .border(width = 5.dp, color = Color.Black, shape = RectangleShape)
                .padding(10.dp)
        ) {
            Column {
                Row {
                    Text(
                        text = "Goals",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))

                Goals()
                /*LazyColumn{

                }*/
            }
        }
    }
}