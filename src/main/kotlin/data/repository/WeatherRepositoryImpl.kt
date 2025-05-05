package org.beijingteam.data.repositories

import org.beijingteam.data.repository.dataSource.RemoteDataSource
import org.beijingteam.domain.entities.Weather
import org.beijingteam.domain.repository.WeatherRepository


class WeatherRepositoryImpl(private val remoteDataSource: RemoteDataSource) : WeatherRepository {

    override suspend fun getWeatherByLongitudeAndLatitude(longitude: Double, latitude: Double): Result<Weather> {
        TODO("Not yet implemented")
    }

    override suspend fun getWeatherByCityName(cityName: String): Result<Weather> {
        TODO("Not yet implemented")
    }
}