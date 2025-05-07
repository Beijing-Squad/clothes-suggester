package domain.usecase

import domain.entity.LocationCoordinate
import org.beijingteam.domain.entity.Weather
import org.beijingteam.domain.repository.WeatherRepository

class GetWeatherByLongitudeAndLatitudeUseCase(
    private val weatherRepository: WeatherRepository
) {

    suspend fun getWeatherByCoordinates(locationCoordinate: LocationCoordinate): Weather {
       return weatherRepository.getWeatherByCoordinate(locationCoordinate)
    }
}