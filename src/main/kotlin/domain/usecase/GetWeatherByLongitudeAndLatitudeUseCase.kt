package org.beijingteam.domain.usecase

import org.beijingteam.domain.repository.WeatherRepository

class GetWeatherByLongitudeAndLatitudeUseCase(
    private val weatherRepository: WeatherRepository) {
}