package org.beijingteam.data.remote.weather.datasource

import domain.entity.LocationCoordinate
import io.ktor.client.*
import org.beijingteam.data.remote.utils.ApiUrls.BASE_WEATHER_URL
import org.beijingteam.data.remote.utils.fetchWithQueryParams
import org.beijingteam.data.remote.weather.dto.WeatherDto

class WeatherRemoteDataSourceImpl(
    private val client: HttpClient,
) : WeatherRemoteDataSource {
    override suspend fun getWeatherByCoordinate(locationCoordinate: LocationCoordinate): WeatherDto {
        val weatherQueryParams = mapOf(
            "latitude" to locationCoordinate.latitude.toString(),
            "longitude" to locationCoordinate.longitude.toString(),
            "daily" to "weather_code,temperature_2m_max,temperature_2m_min",
            "hourly" to "temperature_2m,weather_code",
            "current" to "weather_code,temperature_2m",
            "forecast_days" to "1"
        )
        return client.fetchWithQueryParams<WeatherDto>(BASE_WEATHER_URL, weatherQueryParams)
    }
}