package com.example.taskwise_eventmaster.presentation.task

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.taskwise_eventmaster.DestinationStrings.HOME
import com.example.taskwise_eventmaster.presentation.task.TaskEvent.*
import com.example.taskwise_eventmaster.presentation.utils.RadioButtons
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TaskScreen(
    state: TaskState,
    onEvent: (TaskEvent) -> Unit,
    navController: NavHostController,
    snackbarHostState: SnackbarHostState
) {
    val coroutineScope = rememberCoroutineScope()
    var isAddingTask by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.15f)
                .border(5.dp, color = Color.Black)
                .padding(8.dp)
        )
        {
            Text(
                text = "Tasks",
                modifier = Modifier.align(Alignment.Center),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
            )


            Button(
                modifier = Modifier.align(Alignment.BottomEnd),
                onClick = {
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(
                            message = HOME.screenName,
                            duration = SnackbarDuration.Short
                        )
                    }
                    navController.popBackStack(HOME.destinationString, false)
                }) {
                Text(text = "Back Home")
            }

        }

        RadioButtons(
            modifier = Modifier
                .border(5.dp, color = Color.Black)
                .padding(2.dp)
                .fillMaxWidth(),
            sortType = state.sortType,
            onEvent = onEvent
        )

        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .border(5.dp, color = Color.Black),
            floatingActionButton = {
                FloatingActionButton(onClick = { isAddingTask = true }) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add task")
                }
            }
        ) {
            if (isAddingTask) {
                AddTaskDialog(
                    onEvent = onEvent,
                    onDismiss = { isAddingTask = false },
                )
            }

            LazyColumn {
                items(state.tasks) { task ->
                    TaskCard(
                        task = task,
                        onEvent = onEvent,
                    )
                }
            }
        }
    }
}

