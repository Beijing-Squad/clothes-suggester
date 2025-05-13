package org.beijingteam.data.remote.weather.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrentWeather(
    @SerialName("time")
    val time: String? = null,
    @SerialName("interval")
    val interval: Int? = null,
    @SerialName("temperature_2m")
    val weatherTemperature: Double? = null,
    @SerialName("weather_code")
    val weatherCode: Int? = null
)