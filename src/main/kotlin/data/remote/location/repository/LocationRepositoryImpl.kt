package data.remote.location.repository

import data.remote.location.datasource.LocationRemoteDataSource
import data.remote.location.repository.mapper.CityLocationMapper
import domain.entity.LocationCoordinate
import domain.repository.LocationRepository

class LocationRepositoryImpl(
    private val remoteDataSource: LocationRemoteDataSource,
    private val cityLocationMapper: CityLocationMapper
) : LocationRepository {

    override suspend fun getCoordinateByCityName(cityName: String): LocationCoordinate {
        val locationCoordinateDto = remoteDataSource.getLocationByCityName(cityName)
        return cityLocationMapper.mapDtoToLocationCoordinate(locationCoordinateDto)
    }

}