package com.example.taskwise_eventmaster.data.network.model

data class Event(
    val id: Int,
    val type: String,
    val datetime_utc: String, // error if it is LocalDateTime
    val venue: Venue,
    val performers: List<Performers>,
)

data class EventResponse(
    val events: List<Event>
)

data class Venue(
    val name: String,
    val address: String,
    val city: String,
    val country: String
)

data class Performers(
    val name: String,
    val image: String
)