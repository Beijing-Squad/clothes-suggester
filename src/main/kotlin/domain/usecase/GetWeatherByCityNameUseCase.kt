package org.beijingteam.domain.usecase

import org.beijingteam.domain.entity.Weather
import org.beijingteam.domain.repository.WeatherRepository

class GetWeatherByCityNameUseCase(
    private val weatherRepository: WeatherRepository
) {

    suspend fun getWeatherByCityName(cityName: String): Result<Weather> {
        return weatherRepository.getWeatherByCityName(cityName)
    }

}