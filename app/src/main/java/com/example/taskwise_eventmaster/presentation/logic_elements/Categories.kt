package com.example.taskwise_eventmaster.presentation.logic_elements

import androidx.compose.ui.graphics.Color

object GoalCategories {
    val categories_ = mutableSetOf<GoalCategory>(
        GoalCategory("Common", color = Color.LightGray),
        GoalCategory("Mental health and Wellness", color = Color.Green),
        GoalCategory("Fitness", color = Color.Cyan),
        GoalCategory("Education", color = Color.Magenta),
        GoalCategory("Work", color = Color.Blue),
        GoalCategory("Social life", color = Color.Yellow),
        GoalCategory("Personal life", color = Color.Red),
    )

    fun getCategory(name: String = "Common"): GoalCategory {
        return categories_.find { goalFound ->
            goalFound.name == name
        } ?: categories_.first()
    }

    fun addCategory(name: String, color: Color)
    {
        categories_.add(GoalCategory(name,color))
    }
}