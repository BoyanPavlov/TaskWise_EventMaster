package com.example.taskwise_eventmaster.presentation.goals

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
fun GoalDisplayed(goal: Goal<Any>, modifier: Modifier) {
    Column (modifier = modifier
        .fillMaxWidth()
        .background(color = goal.category.color)
        .alpha(3f)
        .border(width = 2.dp, color = Color.Black, shape = RectangleShape)
        .padding(5.dp)
    ){

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clickable {
                    goal.checkedAsDone = !goal.checkedAsDone
                }
                .padding(end = 16.dp)
                .align(Alignment.Start)
                .background(color = goal.category.color)
                .alpha(3f)
            //TODO : make this one clickable list so U can chose category
        ) {
            Checkbox(
                checked = goal.checkedAsDone,
                onCheckedChange = { goal.checkedAsDone = !goal.checkedAsDone }
            )
            Text(
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                text = goal.category.name + ": "
            )
            Text(
                text = goal.title,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(15.dp))
            Text(text = goal.estimationTime.toString())
        }
    }
}

@Composable
fun Goals() {
    val timeNow = LocalDateTime(2024, 2, 26, 13, 45)
    val goal1 = Goal<Any>("Fit for the summer", "Fitness", timeNow )

    GoalDisplayed(
        goal = goal1, modifier = Modifier
            .fillMaxWidth()
    )
}