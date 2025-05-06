package org.beijingteam.data.remote.datasource

import org.beijingteam.data.model.WeatherDto
import org.beijingteam.data.remote.api.WeatherApiService

class WeatherRemoteDataSourceImpl(
    private val weatherApiClient: WeatherApiService
) : RemoteDataSource {
    override suspend fun fetchWeatherByAPI(cityName: String): WeatherDto {
        return weatherApiClient.fetchWeatherByCity(cityName)
    }

}