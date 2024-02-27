package com.example.taskwise_eventmaster.presentation.logic_elements

import kotlinx.datetime.LocalDateTime

open class Goal<T:Any>(
    public val title: String,
    categoryName: String,
    public val estimationTime: LocalDateTime,
) {
    public var checkedAsDone: Boolean = false
    public val category: GoalCategory = GoalCategories.getCategory(categoryName)
    private lateinit var subfolderOrTask:T
}

//one arm handstand push ->handstand walk-> handstand -> push ups -> push up

//interface Step