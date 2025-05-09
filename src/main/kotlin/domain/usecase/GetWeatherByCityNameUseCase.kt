package domain.usecase

import domain.repository.LocationRepository
import org.beijingteam.domain.entity.Weather
import org.beijingteam.domain.repository.WeatherRepository

class GetWeatherByCityNameUseCase(
    private val weatherRepository: WeatherRepository,
    private val locationRepository: LocationRepository
) {

    suspend fun getWeatherByCityName(cityName: String): Weather {
        return weatherRepository.getWeatherByCoordinate(
            locationRepository.getCoordinateByCityName(cityName)
        )
    }
}