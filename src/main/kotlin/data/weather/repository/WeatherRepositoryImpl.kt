package org.beijingteam.data.weather.repository

import domain.entity.LocationCoordinate
import org.beijingteam.data.weather.repository.mapper.WeatherMapper
import org.beijingteam.data.weather.datasource.WeatherRemoteDataSource
import org.beijingteam.domain.entity.Weather
import org.beijingteam.domain.repository.WeatherRepository

class WeatherRepositoryImpl(
    private val remoteDataSource: WeatherRemoteDataSource,
    private val weatherMapper: WeatherMapper
) : WeatherRepository {
    override suspend fun getWeatherByCoordinate(locationCoordinate: LocationCoordinate): Weather {
        TODO("Not yet implemented")
    }

}