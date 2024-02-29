package com.example.taskwise_eventmaster.presentation.utils

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import java.time.LocalDateTime

class DataTime() {

    private var years: Int = 0
    private var months: Int = 0
    private var days: Int = 0
    private var hours: Int = 0
    private var minutes: Int = 0
    private fun isLeapYear(year: Int): Boolean {
        return when {
            year % 400 == 0 -> true
            year % 100 == 0 -> false
            year % 4 == 0 -> true
            else -> false
        }
    }

    @Composable
    fun readDataField(): LocalDateTime {

        var collectionOfTimeVars by remember {
            mutableStateOf(
                listOf(years, months, days, hours, minutes)
            )
        }

        Row {
            minutes = readValueField<Int>(1,"Minutes",InputTypeLambdaHandling.intHandling)
            days = readValueField<Int>(1,"Days",InputTypeLambdaHandling.intHandling)
            hours = readValueField<Int>(1,"Hours",InputTypeLambdaHandling.intHandling)
            months = readValueField<Int>(1,"Months",InputTypeLambdaHandling.intHandling)
            years = readValueField<Int>(1,"Years",InputTypeLambdaHandling.intHandling)
        }
        
        validateDate()

        val result: LocalDateTime = LocalDateTime.of(
            years,
            months,
            days,
            hours,
            minutes
        )

        return result
    }

    private fun validateDate() {
        val monthsWith30Days = setOf<Int>(4, 6, 9, 11)
        //val monthsWith31Days = setOf<Int>(1, 3, 5, 7, 8, 10, 12)
        val isLeapYear = isLeapYear(years)
        var timeNow: LocalDateTime = LocalDateTime.now()

        //TODO use timeNow

        if (minutes > 60) {
            hours += minutes / 60
            minutes %= 60
        }

        if (hours > 24) {
            days += hours / 24
            hours %= 24
        }

        if (days > 28) {
            if (months == 2) {
                if (isLeapYear) {
                    months += days / 29
                    days %= 29
                } else {
                    months += days / 28
                    days %= 28
                }
            } else {
                if (monthsWith30Days.contains(months)) {
                    months += days / 30
                    days %= 30
                } else {
                    months += days / 31
                    days %= 31
                }
            }
        }

        if (months > 12) {
            years += months / 12
            months %= 12
        }
    }
}