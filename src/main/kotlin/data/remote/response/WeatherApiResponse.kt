package data.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class WeatherApiResponse(
    val currentWeather: CurrentWeatherResponse
)