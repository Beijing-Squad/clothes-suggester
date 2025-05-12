package org.beijingteam.data.remote.weather.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherDto(
    @SerialName("latitude")
    val latitude: Double? = null,
    @SerialName("longitude")
    val longitude: Double? = null,
    @SerialName("generationtime_ms")
    val generationTime: Double? = null,
    @SerialName("utc_offset_seconds")
    val utcOffsetSeconds: Int? = null,
    @SerialName("timezone")
    val timeZone: String? = null,
    @SerialName("timezone_abbreviation")
    val timeZoneAbbreviation: String? = null,
    @SerialName("elevation")
    val elevation: Double? = null,
    @SerialName("current_units")
    val currentWeatherUnits: CurrentWeatherUnits? = null,
    @SerialName("current")
    val currentWeather: CurrentWeather? = null,
)