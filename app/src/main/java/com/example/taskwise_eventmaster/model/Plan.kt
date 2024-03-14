package com.example.taskwise_eventmaster.model

import com.example.taskwise_eventmaster.model.categories.GoalCategories
import com.example.taskwise_eventmaster.model.categories.GoalCategory
import java.time.LocalDateTime

abstract class Plan(
    val title: String,
    categoryName: String,
    val estimationTime: LocalDateTime,
) {
    var checkedAsDone: Boolean = false
    val category: GoalCategory = GoalCategories.getCategory(categoryName)
}