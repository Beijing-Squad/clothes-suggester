package org.beijingteam.data.repository

import org.beijingteam.data.remote.datasource.WeatherRemoteDataSourceImpl
import org.beijingteam.data.mapper.WeatherMapper
import org.beijingteam.domain.entity.Weather
import org.beijingteam.domain.repository.WeatherRepository

class WeatherRepositoryImpl(
    private val remoteWeatherDataSource: WeatherRemoteDataSourceImpl,
    private val weatherMapper: WeatherMapper
) : WeatherRepository {

    override suspend fun getWeatherByLongitudeAndLatitude(longitude: Double, latitude: Double): Result<Weather> {
        TODO("Not yet implemented")
    }

    override suspend fun getWeatherByCityName(cityName: String): Result<Weather> {
        TODO("Not yet implemented")
    }
}