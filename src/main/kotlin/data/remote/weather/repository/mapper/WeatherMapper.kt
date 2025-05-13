package org.beijingteam.data.remote.weather.repository.mapper

import domain.exception.MissingTemperatureException
import domain.exception.MissingWeatherConditionException
import org.beijingteam.data.remote.weather.dto.WeatherDto
import org.beijingteam.domain.type.TemperatureCategory
import org.beijingteam.domain.entity.Weather
import org.beijingteam.domain.type.WeatherCondition

class WeatherMapper {

    fun mapWeatherDtoToWeatherEntity(weatherDto: WeatherDto): Weather {
        val temperature = weatherDto.currentWeather?.weatherTemperature
            ?: throw MissingTemperatureException()

        val weatherCode = weatherDto.currentWeather.weatherCode
            ?: throw MissingWeatherConditionException()

        val condition = mapWeatherCodeToWeatherCondition(weatherCode)
        val category = mapWeatherTemperatureToTemperatureCategory(temperature)

        return Weather(
            temperature = temperature,
            weatherCondition = condition,
            temperatureCategory = category
        )
    }

    private fun mapWeatherCodeToWeatherCondition(weatherCode: Int): WeatherCondition {
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

    private fun mapWeatherTemperatureToTemperatureCategory(temperature: Double): TemperatureCategory {
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