package org.beijingteam.data.remote.weather.repository

import domain.entity.LocationCoordinate
import org.beijingteam.data.remote.weather.datasource.WeatherRemoteDataSource
import org.beijingteam.data.remote.weather.repository.mapper.WeatherMapper
import org.beijingteam.domain.entity.Weather
import org.beijingteam.domain.repository.WeatherRepository

class WeatherRepositoryImpl(
    private val remoteDataSource: WeatherRemoteDataSource,
    private val weatherMapper: WeatherMapper
) : WeatherRepository {
    override suspend fun getWeatherByCoordinate(locationCoordinate: LocationCoordinate): Weather {
        val weatherCondition=remoteDataSource.getWeatherByCoordinate(locationCoordinate)
        return weatherMapper.mapToDomain(weatherCondition)
    }
}