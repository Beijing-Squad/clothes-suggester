package org.beijingteam.domain.entity

import org.beijingteam.domain.type.TemperatureCategory
import org.beijingteam.domain.type.WeatherCondition

data class Weather(
    val temperature: Double,
    val weatherCondition: WeatherCondition,
    val temperatureCategory: TemperatureCategory
)