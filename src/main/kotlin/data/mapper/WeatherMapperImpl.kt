package org.beijingteam.data.mapper

import org.beijingteam.domain.entity.enums.TemperatureCategory
import org.beijingteam.domain.entity.enums.WeatherCondition

class WeatherMapperImpl : WeatherMapper {
    override fun mapCondition(weatherCode: Int): WeatherCondition {
        return when (weatherCode) {
            0 -> WeatherCondition.CLEAR
            1, 2, 3 -> WeatherCondition.PARTLY_CLOUDY
            45, 48 -> WeatherCondition.FOG
            51, 53, 55 -> WeatherCondition.DRIZZLE
            61, 63, 65, 80, 81, 82 -> WeatherCondition.RAIN
            71, 73, 75, 77, 85, 86 -> WeatherCondition.SNOW
            95, 96, 99 -> WeatherCondition.THUNDERSTORM
            else -> WeatherCondition.UNKNOWN
        }
    }

    override fun mapTemperatureCategory(temperature: Double): TemperatureCategory {
        return when {
            temperature < 0 -> TemperatureCategory.FREEZING
            temperature < 10 -> TemperatureCategory.COLD
            temperature < 20 -> TemperatureCategory.COOL
            temperature < 25 -> TemperatureCategory.MILD
            temperature < 30 -> TemperatureCategory.WARM
            else -> TemperatureCategory.HOT
        }
    }
}