package domain.useCase

import domain.entity.LocationCoordinate
import domain.repository.LocationRepository

class GetCoordinateByCityNameUseCase(
    private val locationRepository: LocationRepository,
) {

    suspend fun getCoordinateByCityName(cityName: String): LocationCoordinate {
        return locationRepository.getCoordinateByCityName(cityName)
    }

}

