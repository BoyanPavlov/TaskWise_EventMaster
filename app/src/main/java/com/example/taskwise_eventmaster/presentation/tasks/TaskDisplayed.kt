package com.example.taskwise_eventmaster.presentation.tasks

import RatingBarUsage
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.datetime.LocalDateTime


@Composable
fun TaskDisplayed(task: Task, modifier: Modifier = Modifier) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .border(width = 2.dp, color = Color.Black, shape = RectangleShape)
            .then(modifier)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clickable {
                    task.checkedAsDone = !task.checkedAsDone
                }
                .padding(end = 16.dp)
                .align(Alignment.Start)
        ) {
            Checkbox(
                checked = task.checkedAsDone,
                onCheckedChange = { task.checkedAsDone = !task.checkedAsDone }
            )
            Text(
                text = task.title,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(15.dp))
            Text(text = task.estimationTime.toString())

        }

        if (!task.description.isEmpty()) {
            Row(
                modifier = Modifier
                    .background(color = Color.LightGray)
                    .padding(10.dp)
            ) {
                Text(
                    text = "Description: ",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Text(text = task.description)
            }
        }

        Row(modifier.align(Alignment.CenterHorizontally)) {
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .background(color = task.category.color)
                    .alpha(3f)
                    .padding(5.dp)
            )
            {
                //TODO : make this one clickable list so U can chose category
                Column(modifier = Modifier.align(Alignment.BottomStart)){
                    if (!task.goalName.isEmpty()) {
                        Text(
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            text = task.goalName
                        )
                    }
                    Text(
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        text = task.category.name
                    )
                }
                Box(modifier = Modifier.align(Alignment.CenterEnd))
                {
                    RatingBarUsage()
                }
            }
        }
    }
}

@Composable
fun Tasks() {
    /*var tasks by remember {
        mutableStateOf(listOf<Task>())
    }*/
    val description =
        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s...."
    val timeNow = LocalDateTime(2024, 2, 26, 13, 45)
    val task1 = Task("Go to the gym", "Fitness",timeNow, 3,"Fit for the summer", description = description)

    TaskDisplayed(
        task = task1, modifier = Modifier
            .fillMaxWidth()
    )
}