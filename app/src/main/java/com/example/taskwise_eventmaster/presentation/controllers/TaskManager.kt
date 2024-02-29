package com.example.taskwise_eventmaster.presentation.controllers

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import com.example.taskwise_eventmaster.presentation.tasks.Task

//class which will be used for storing goals and tasks.
class TaskManager {

    @Composable
    fun Tasks() {
        val checkboxes = remember {
            mutableStateListOf<Task>()
        }
    }
    //addGoal
    //addTask
}