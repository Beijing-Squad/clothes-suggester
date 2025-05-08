package org.beijingteam.data.remote.weather.datasource

import domain.entity.LocationCoordinate
import io.ktor.client.*
import kotlinx.serialization.json.Json
import org.beijingteam.data.remote.weather.dto.WeatherDto
import org.beijingteam.data.remote.utils.getWithParams
import org.beijingteam.data.remote.utils.ApiUrls.WEATHER_URL

class WeatherRemoteDataSourceImpl(
    private val client: HttpClient,
    private val json: Json,
) : WeatherRemoteDataSource {
    override suspend fun getWeatherByCoordinate(locationCoordinate: LocationCoordinate): WeatherDto {
        val params = mapOf(
            "latitude" to locationCoordinate.latitude.toString(),
            "longitude" to locationCoordinate.longitude.toString(),
            "daily" to "weather_code,temperature_2m_max,temperature_2m_min",
            "hourly" to "temperature_2m,weather_code",
            "current" to "weather_code,temperature_2m",
            "forecast_days" to "1"
        )
        return client.getWithParams<WeatherDto>(WEATHER_URL, params, json)
    }
}