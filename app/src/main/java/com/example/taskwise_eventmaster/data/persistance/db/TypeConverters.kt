package com.example.taskwise_eventmaster.data.persistance.db

import androidx.room.TypeConverter
import kotlinx.datetime.toJavaLocalDateTime
import kotlinx.datetime.toLocalDateTime
import java.time.LocalDateTime

class Converters {
    @TypeConverter
    fun fromTimestamp(value: String?): LocalDateTime? {
        return (value?.toLocalDateTime())?.toJavaLocalDateTime()
    }

    @TypeConverter
    fun dateToTimeStamp(dateTime: LocalDateTime?): String? {
        return dateTime?.toString()
    }
}