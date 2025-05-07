package domain.useCase

import domain.repository.LocationRepository
import org.beijingteam.domain.repository.WeatherRepository

class GetWeatherByCityNameUseCase(
    private val locationRepository: LocationRepository
) {
}