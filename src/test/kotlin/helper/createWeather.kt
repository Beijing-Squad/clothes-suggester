package helper

import org.beijingteam.domain.type.TemperatureCategory
import org.beijingteam.domain.entity.Weather
import org.beijingteam.domain.type.WeatherCondition

fun createWeather(
    temperature: Double = 20.0,
    weatherCondition: WeatherCondition = WeatherCondition.CLEAR,
    temperatureCategory: TemperatureCategory = TemperatureCategory.MILD
): Weather {
    return Weather(
        temperature = temperature,
        weatherCondition = weatherCondition,
        temperatureCategory = temperatureCategory
    )
}