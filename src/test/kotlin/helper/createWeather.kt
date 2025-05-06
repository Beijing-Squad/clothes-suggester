package helper

import org.beijingteam.domain.entity.Weather
import org.beijingteam.domain.entity.enums.TemperatureCategory
import org.beijingteam.domain.entity.enums.WeatherCondition

fun createWeather(
    temperature: Double = 20.0,
    relativeHumidity: Double = 50.0,
    weatherCondition: WeatherCondition = WeatherCondition.CLEAR,
    temperatureCategory: TemperatureCategory = TemperatureCategory.MILD
): Weather {
    return Weather(
        temperature = temperature,
        relativeHumidity = relativeHumidity,
        weatherCondition = weatherCondition,
        temperatureCategory = temperatureCategory
    )
}