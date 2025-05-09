package org.beijingteam.data.remote.weather.datasource

import domain.entity.LocationCoordinate
import org.beijingteam.data.remote.weather.dto.WeatherDto

interface WeatherRemoteDataSource {
    suspend fun getWeatherByCoordinate(locationCoordinate: LocationCoordinate): WeatherDto
}