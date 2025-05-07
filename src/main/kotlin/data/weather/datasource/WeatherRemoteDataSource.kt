package org.beijingteam.data.weather.datasource

import domain.entity.LocationCoordinate
import org.beijingteam.data.weather.dto.WeatherDto

interface WeatherRemoteDataSource {
    suspend fun getWeatherByCoordinate(locationCoordinate: LocationCoordinate): WeatherDto
}