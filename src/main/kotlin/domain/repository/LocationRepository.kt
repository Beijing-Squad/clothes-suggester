package domain.repository

import domain.entity.LocationCoordinate

interface LocationRepository {
    suspend fun getCoordinateByCityName(cityName: String) : LocationCoordinate
}