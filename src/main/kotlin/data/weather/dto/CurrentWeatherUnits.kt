package org.beijingteam.data.weather.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrentWeatherUnits(
    val time: String?,
    @SerialName("interval")
    val interval: String?,
    @SerialName("temperature_2m")
    val temperature2m: String?,
    @SerialName("weather_code")
    val weatherCode: String?
)