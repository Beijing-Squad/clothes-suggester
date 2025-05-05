package org.beijingteam.data.repository.dataSource

import data.dto.WeatherDto

interface RemoteDataSource {
    suspend fun fetchWeatherByAPI():WeatherDto
}