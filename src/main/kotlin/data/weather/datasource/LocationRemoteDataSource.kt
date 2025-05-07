package data.weather.datasource

import data.weather.dto.CityLocationDto

interface LocationRemoteDataSource {
    suspend fun getLocationByCityName(cityName: String) : CityLocationDto
}