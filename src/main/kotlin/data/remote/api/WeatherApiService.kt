package org.beijingteam.data.remote.api

import org.beijingteam.data.model.WeatherDto
import data.remote.response.GeocodingApiResponse
import data.remote.response.WeatherApiResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import org.beijingteam.data.mapper.WeatherMapper
import org.beijingteam.data.utils.ApiUrls.GEOCODING_URL
import org.beijingteam.data.utils.ApiUrls.WEATHER_URL

class WeatherApiService(
    private val client: HttpClient,
    private val weatherMapper: WeatherMapper
) {
    suspend fun fetchWeatherByCity(cityName: String): WeatherDto {
        val coordinates = getCoordinatesForCity(cityName)
            ?: throw IllegalArgumentException("City not found: $cityName")

        val response: WeatherApiResponse = client.get(WEATHER_URL) {
            parameter("latitude", coordinates.first)
            parameter("longitude", coordinates.second)
            parameter("current_weather", true)
        }.body()

        return WeatherDto(
            temperature = response.currentWeather.temperature,
            relativeHumidity = response.currentWeather.relativeHumidity,
            weatherCode = response.currentWeather.weatherCode,
            mapper = weatherMapper
        )
    }

    private suspend fun getCoordinatesForCity(cityName: String): Pair<Double, Double>? {
        val response: GeocodingApiResponse = client.get(GEOCODING_URL) {
            parameter("name", cityName)
        }.body()

        return response.results?.firstOrNull()?.let { result ->
            Pair(result.latitude, result.longitude)
        }
    }

}