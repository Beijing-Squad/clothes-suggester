package domain.useCase

import org.beijingteam.domain.repository.WeatherRepository

class GetWeatherByLongitudeAndLatitudeUseCase(
    private val weatherRepository: WeatherRepository
) {
}