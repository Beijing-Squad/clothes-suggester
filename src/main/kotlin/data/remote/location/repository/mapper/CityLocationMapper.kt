package data.remote.location.repository.mapper

import data.remote.location.dto.CityLocationDto
import domain.entity.LocationCoordinate
import domain.exception.MissingLocationException

class CityLocationMapper {
    fun mapDtoToLocationCoordinate(cityLocationDto: CityLocationDto): LocationCoordinate {
        val citiesCoordinates = cityLocationDto.citiesCoordinates ?: throw MissingLocationException()
        val firstCity = citiesCoordinates.firstOrNull() ?: throw MissingLocationException()
        val latitude = firstCity.latitude ?: throw MissingLocationException()
        val longitude = firstCity.longitude ?: throw MissingLocationException()
        return LocationCoordinate(latitude, longitude)
    }
}