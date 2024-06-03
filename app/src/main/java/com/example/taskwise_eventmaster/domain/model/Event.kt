package com.example.taskwise_eventmaster.domain.model

import java.time.LocalDateTime

data class Event(
    val id: Int,
    val type: String,
    val name: String,
    val dateTimeUtc: LocalDateTime,
    val address: String,
    val city: String,
    val country: String,
    val thumbnails: List<Thumbnail>,
) {

    data class Thumbnail(
        val thumbnailUrl: String
    )
}
