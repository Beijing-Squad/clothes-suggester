package data.dto

import org.beijingteam.data.mapper.WeatherMapper
import org.beijingteam.domain.entity.enums.TemperatureCategory
import org.beijingteam.domain.entity.enums.WeatherCondition


data class WeatherDto(
    val temperature: Double,
    val relativeHumidity: Double,
    val weatherCode: Int,
    private val mapper: WeatherMapper
) {
    val weatherCondition: WeatherCondition
        get() = mapper.mapCondition(weatherCode)

    val temperatureCategory: TemperatureCategory
        get() = mapper.mapTemperatureCategory(temperature)
}
