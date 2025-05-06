package org.beijingteam.domain.usecase

import org.beijingteam.domain.entity.Weather
import org.beijingteam.domain.repository.WeatherRepository

class GetWeatherByLongitudeAndLatitudeUseCase(
    private val weatherRepository: WeatherRepository
) {

    suspend fun getWeatherByCoordinates(longitude: Double, latitude: Double): Result<Weather> {
       TODO()
    }
}