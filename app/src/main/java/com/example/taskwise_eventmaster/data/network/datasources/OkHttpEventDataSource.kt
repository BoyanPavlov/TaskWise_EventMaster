package com.example.taskwise_eventmaster.data.network.datasources

import com.example.taskwise_eventmaster.data.network.model.EventResponse
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OkHttpEventDataSource @Inject constructor() : RemoteEventDataSource {
    private val client = OkHttpClient()

    override suspend fun getEvents(): EventResponse? {
        val result = getEventsInfoString()

        return try {
            val gson = Gson()
            val event = gson.fromJson(result, EventResponse::class.java)
            event
        } catch (e: Exception) {
            null
        }
    }

    private suspend fun getEventsInfoString(): String? = withContext(Dispatchers.IO) {
        val request = Request.Builder()
            .url("https://api.seatgeek.com/2/events/?client_id=NDA5ODQ0Nzh8MTcxMzE5NDg2NC43NTcwMDE&client_secret=64393bf8da39f34f22cec93e76afe8cfe4c8d855e01a51fe14c0268f929e2cf3")
            .build()

        return@withContext try {
            val response: Response = client.newCall(request).execute()
            if (response.isSuccessful) {
                response.body?.string()
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }
}
