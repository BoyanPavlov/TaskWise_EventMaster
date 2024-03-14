package com.example.taskwise_eventmaster.presentation.planning_view

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.taskwise_eventmaster.service.authorization.AuthService
import javax.inject.Inject

class PlanningViewModel @Inject constructor(
    private val authService: AuthService
) : ViewModel() {
    var state by mutableStateOf(PlanningViewState())
        private set

    init {
        state = state.copy(
            userData = authService.getSignedInUser()
        )
    }

    fun onEvent(event: PlanningViewEvent) = when (event) {
        is PlanningViewEvent.OnCreateTask -> createTask()
        is PlanningViewEvent.OnCreateGoal -> createGoal()
        is PlanningViewEvent.OnDeleteTask -> deleteTask()
        is PlanningViewEvent.OnDeleteGoal -> deleteGoal()
    }

    private fun createTask(): Any {
        TODO("Not yet implemented")
    }

    private fun createGoal(): Any {
        TODO("Not yet implemented")
    }

    private fun deleteTask(): Any {
        TODO("Not yet implemented")
    }

    private fun deleteGoal(): Any {
        TODO("Not yet implemented")
    }

}