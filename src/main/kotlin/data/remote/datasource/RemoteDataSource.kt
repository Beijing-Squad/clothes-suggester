package org.beijingteam.data.remote.datasource

import org.beijingteam.data.dto.WeatherDto

interface RemoteDataSource {
    suspend fun fetchWeatherByAPI(cityName: String): WeatherDto
}