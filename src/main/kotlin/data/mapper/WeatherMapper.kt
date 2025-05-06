package org.beijingteam.data.mapper

import org.beijingteam.domain.entity.enums.TemperatureCategory
import org.beijingteam.domain.entity.enums.WeatherCondition

interface WeatherMapper {
    fun mapCondition(weatherCode: Int): WeatherCondition
    fun mapTemperatureCategory(temperature: Double): TemperatureCategory
}