package domain.useCase

import domain.repository.LocationRepository
import org.beijingteam.domain.entity.Weather
import org.beijingteam.domain.repository.WeatherRepository

class GetWeatherByCityNameUseCase(
    private val locationRepository: LocationRepository
) {

    suspend fun getWeatherByCityName(cityName: String): Result<Weather> {
        TODO("Not yet implemented")
    }

}