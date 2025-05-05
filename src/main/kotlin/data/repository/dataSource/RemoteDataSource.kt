package org.beijingteam.data.repository.dataSource

import org.beijingteam.domain.entities.Weather

interface RemoteDataSource {
    suspend fun fetchWeatherByAPI():Weather
}