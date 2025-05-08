package org.beijingteam.data.remote.weather.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherDto(
    val latitude: Double?,
    val longitude: Double?,
    @SerialName("generationtime_ms")
    val generationTime: Double?,
    @SerialName("utc_offset_seconds")
    val utcOffsetSeconds: Int?,
    @SerialName("timezone")
    val timeZone: String?,
    @SerialName("timezone_abbreviation")
    val timeZoneAbbreviation: String?,
    @SerialName("elevation")
    val elevation: Double?,
    @SerialName("current_units")
    val currentWeatherUnits: CurrentWeatherUnits?,
    @SerialName("current")
    val currentWeather: CurrentWeather?,
)