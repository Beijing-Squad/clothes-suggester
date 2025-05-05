package org.beijingteam.domain.repository

import org.beijingteam.domain.entity.Weather

interface WeatherRepository {
    suspend fun getWeatherByLongitudeAndLatitude(longitude: Double, latitude: Double): Result<Weather>
    suspend fun getWeatherByCityName(cityName: String): Result<Weather>
}