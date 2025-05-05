package org.beijingteam.data.remote

import org.beijingteam.data.repository.dataSource.RemoteDataSource
import org.beijingteam.domain.entities.Weather

class RemoteWeatherDataSource: RemoteDataSource {
    override suspend fun fetchWeatherByAPI(): Weather {
        TODO()
    }
    companion object{
        private const val BASE_URL ="https://api.open-meteo.com/v1/forecast"
    }
}