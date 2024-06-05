package com.example.taskwise_eventmaster.data.network.model

import com.google.gson.annotations.SerializedName

data class Event(
    val id: Int,
    val type: String,
    @SerializedName("datetime_utc") val dataTimeUtc: String,
    val venue: Venue,
    val performers: List<Performers>,
)

data class EventResponse(
    val events: List<Event>
)

data class Venue(
    val name: String,
    val address: String?,
    val city: String?,
    val country: String?
)

data class Performers(
    val name: String?,
    val image: String?
)