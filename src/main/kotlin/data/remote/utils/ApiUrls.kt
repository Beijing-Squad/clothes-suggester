package org.beijingteam.data.remote.utils

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.serialization.json.Json

object ApiUrls {
    const val WEATHER_URL = "https://api.open-meteo.com/v1/forecast"
    const val BASE_CITY_LOCATION_URL = "https://geocoding-api.open-meteo.com/v1/search"
}

suspend inline fun <reified T> HttpClient.getWithParams(
    baseUrl: String,
    params: Map<String, String> = emptyMap(),
    json: Json
): T {
    val response = get(baseUrl) {
        url {
            params.forEach { (key, value) ->
                parameters.append(key, value)
            }
        }
    }
    return json.decodeFromString<T>(response.bodyAsText())
}