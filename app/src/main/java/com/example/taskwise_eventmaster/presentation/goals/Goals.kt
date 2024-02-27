package com.example.taskwise_eventmaster.presentation.goals

import com.example.taskwise_eventmaster.presentation.categories.GoalCategories
import com.example.taskwise_eventmaster.presentation.categories.GoalCategory
import kotlinx.datetime.LocalDateTime

open class Goal<T:Any>(
    public val title: String,
    categoryName: String,
    public val estimationTime: LocalDateTime,
    val goalName:String = String(),
) {
    public var checkedAsDone: Boolean = false
    public val category: GoalCategory = GoalCategories.getCategory(categoryName)
    private lateinit var subfolderOrTask:T
}

//one arm handstand push ->handstand walk-> handstand -> push ups -> push up

//interface Step