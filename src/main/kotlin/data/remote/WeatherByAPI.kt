package org.beijingteam.data.remote

import data.dto.WeatherDto
import org.beijingteam.data.repository.dataSource.RemoteDataSource

class RemoteWeatherDataSource : RemoteDataSource {
    override suspend fun fetchWeatherByAPI(): WeatherDto {
        TODO()
    }

    companion object {
        private const val BASE_URL = "https://api.open-meteo.com/v1/forecast"
    }
}