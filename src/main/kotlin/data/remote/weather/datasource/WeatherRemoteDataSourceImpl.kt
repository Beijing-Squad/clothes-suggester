package org.beijingteam.data.remote.weather.datasource

import domain.entity.LocationCoordinate
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.serialization.json.Json
import org.beijingteam.data.remote.weather.dto.WeatherDto
import org.beijingteam.data.remote.utils.ApiUrls.WEATHER_URL

class WeatherRemoteDataSourceImpl(
    private val client: HttpClient,
    private val json: Json,
) : WeatherRemoteDataSource {
    override suspend fun getWeatherByCoordinate(locationCoordinate: LocationCoordinate): WeatherDto {
        val response = client.get(WEATHER_URL) {
            url {
                parameters.append("latitude", locationCoordinate.latitude.toString())
                parameters.append("longitude", locationCoordinate.longitude.toString())
                parameters.append("daily", "weather_code,temperature_2m_max,temperature_2m_min")
                parameters.append("hourly", "temperature_2m,weather_code")
                parameters.append("current", "weather_code,temperature_2m")
                parameters.append("forecast_days", "1")
            }
        }
        return json.decodeFromString<WeatherDto>(response.bodyAsText())
    }
}