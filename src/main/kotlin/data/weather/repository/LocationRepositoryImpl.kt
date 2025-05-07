package data.weather.repository

import data.weather.datasource.LocationRemoteDataSource
import domain.entity.LocationCoordinate
import domain.repository.LocationRepository
import org.beijingteam.data.weather.repository.mapper.CityLocationMapper

class LocationRepositoryImpl(
    private val remoteDataSource: LocationRemoteDataSource,
    private val cityLocationMapper: CityLocationMapper
) : LocationRepository {

    override suspend fun getCoordinateByCityName(cityName: String): LocationCoordinate {
        val locationCoordinateDto = remoteDataSource.getLocationByCityName(cityName)
        return cityLocationMapper.mapDtoToLocationCoordinate(locationCoordinateDto)
    }

}