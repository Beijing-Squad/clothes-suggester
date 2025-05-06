package domain.useCase

import org.beijingteam.domain.repository.WeatherRepository

class GetWeatherByCityNameUseCase(
    private val weatherRepository: WeatherRepository
) {
}