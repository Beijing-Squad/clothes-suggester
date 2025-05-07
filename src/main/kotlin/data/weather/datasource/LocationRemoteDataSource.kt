package data.weather.datasource

import data.weather.dto.CityLocationDto

interface LocationRemoteDataSource {
    suspend fun getLocationByCityAndCountry(cityName: String) : CityLocationDto
}