package org.beijingteam.domain.entity

import org.beijingteam.domain.entity.enums.TemperatureCategory
import org.beijingteam.domain.entity.enums.WeatherCondition


data class Weather(
    val temperature: Double,
    val relativeHumidity: Int,
    val weatherCondition: WeatherCondition,
    val temperatureCategory: TemperatureCategory
)