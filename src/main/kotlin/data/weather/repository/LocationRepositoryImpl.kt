package data.weather.repository

import data.weather.datasource.LocationRemoteDataSource
import data.weather.mapper.CityLocationMapper
import domain.entity.LocationCoordinate
import domain.repository.LocationRepository

class LocationRepositoryImpl(
    private val remoteDataSource: LocationRemoteDataSource,
    private val cityLocationMapper: CityLocationMapper
) : LocationRepository {
    override suspend fun getCoordinateByCityName(cityName: String): LocationCoordinate {
        TODO("Not yet implemented")
    }

}