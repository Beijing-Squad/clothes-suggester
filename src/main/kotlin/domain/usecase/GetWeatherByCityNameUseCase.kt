package org.beijingteam.domain.usecase

import org.beijingteam.domain.repository.WeatherRepository

class GetWeatherByCityNameUseCase(
    private val weatherRepository: WeatherRepository
) {
}