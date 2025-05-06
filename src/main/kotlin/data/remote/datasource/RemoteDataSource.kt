package org.beijingteam.data.remote.datasource

import org.beijingteam.data.model.WeatherDto

interface RemoteDataSource {
    suspend fun fetchWeatherByAPI(cityName: String): WeatherDto
}