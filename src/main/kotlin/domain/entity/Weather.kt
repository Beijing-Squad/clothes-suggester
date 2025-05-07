package org.beijingteam.domain.entity

data class Weather(
    val temperature: Double,
    val weatherCondition: WeatherCondition,
    val temperatureCategory: TemperatureCategory
)