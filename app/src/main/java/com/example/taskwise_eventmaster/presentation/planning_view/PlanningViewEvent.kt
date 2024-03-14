package com.example.taskwise_eventmaster.presentation.planning_view

sealed interface PlanningViewEvent {
    data object OnCreateTask : PlanningViewEvent
    data object OnCreateGoal : PlanningViewEvent
    data object OnDeleteTask : PlanningViewEvent
    data object OnDeleteGoal : PlanningViewEvent
}