package com.example.taskwise_eventmaster.presentation.tasks

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
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskwise_eventmaster.presentation.controllers.NavigationController
import com.example.taskwise_eventmaster.presentation.utils.DataTime
import com.example.taskwise_eventmaster.presentation.utils.InputTypeLambdaHandling
import com.example.taskwise_eventmaster.presentation.utils.readValueField
import java.time.LocalDateTime



@Composable
fun AddNewTaskPage(navigationController: NavigationController) {
    //TODO remember, where
    var title: String
    var categoryName: String
    var estimationTime: LocalDateTime
    var levelOfHardness: Int
    var goalName: String = String()
    var description: String = String()

    val dataTime:DataTime = DataTime()

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(//title part
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.15f)
                .border(width = 5.dp, color = Color.Black, shape = RectangleShape)
                .padding(10.dp)

        ) {
            Row(modifier = Modifier.align(Alignment.TopCenter)) {
                Text(
                    text = "Create new Task!",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(modifier = Modifier.align(Alignment.BottomEnd)) {
                Button(onClick = navigationController.goBack) {
                    Text(text = "TODO:Cancel, now works as go back")
                }
            }
        }

        title = readValueField<String>(1,"Title", InputTypeLambdaHandling.stringHandling)
        //TODO make this one drop down menu
        categoryName = readValueField<String>(1,"Category Name", InputTypeLambdaHandling.stringHandling)
        estimationTime = dataTime.readDataField()
        levelOfHardness = readValueField<Int>(1,"Level of difficulty", InputTypeLambdaHandling.intHandling)
        goalName = readValueField<String>(1,"Paren goal name", InputTypeLambdaHandling.stringHandling)
        description = readValueField<String>(1,"Description", InputTypeLambdaHandling.stringHandling)
    }
}


