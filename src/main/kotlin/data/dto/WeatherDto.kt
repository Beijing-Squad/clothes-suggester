package data.dto

import org.beijingteam.domain.entity.enums.TemperatureCategory
import org.beijingteam.domain.entity.enums.WeatherCondition


data class WeatherDto(
    val temperature: Double,
    val relativeHumidity: Double,
    val weatherCode: Int,
    val isDay: Boolean,
) {
    val condition: WeatherCondition
        get() = when (weatherCode) {
            0 -> WeatherCondition.CLEAR
            1, 2, 3 -> WeatherCondition.PARTLY_CLOUDY
            45, 48 -> WeatherCondition.FOG
            51, 53, 55 -> WeatherCondition.DRIZZLE
            61, 63, 65, 80, 81, 82 -> WeatherCondition.RAIN
            71, 73, 75, 77, 85, 86 -> WeatherCondition.SNOW
            95, 96, 99 -> WeatherCondition.THUNDERSTORM
            else -> WeatherCondition.UNKNOWN
        }

    val temperatureCategory: TemperatureCategory
        get() = when {
            temperature < 0 -> TemperatureCategory.FREEZING
            temperature < 10 -> TemperatureCategory.COLD
            temperature < 20 -> TemperatureCategory.COOL
            temperature < 25 -> TemperatureCategory.MILD
            temperature < 30 -> TemperatureCategory.WARM
            else -> TemperatureCategory.HOT
        }
}
