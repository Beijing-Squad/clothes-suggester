package org.beijingteam.data.repository

import org.beijingteam.data.mapper.WeatherMapper
import org.beijingteam.data.repository.dataSource.RemoteDataSource
import org.beijingteam.domain.entity.Weather
import org.beijingteam.domain.repository.WeatherRepository

class WeatherRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val weatherMapper: WeatherMapper
) : WeatherRepository {

    override suspend fun getWeatherByLongitudeAndLatitude(longitude: Double, latitude: Double): Result<Weather> {
        TODO("Not yet implemented")
    }

    override suspend fun getWeatherByCityName(cityName: String): Result<Weather> {
        TODO("Not yet implemented")
    }
}