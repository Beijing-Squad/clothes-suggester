package data.remote.location.datasource

import data.remote.location.dto.CityLocationDto

interface LocationRemoteDataSource {
    suspend fun getLocationByCityName(cityName: String) : CityLocationDto
}