package org.beijingteam.data.weather.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrentWeather(
    val time: String?,
    @SerialName("interval")
    val interval: Int?,
    @SerialName("temperature_2m")
    val temperature2m: Double?,
    @SerialName("weather_code")
    val weatherCode: Int?
)