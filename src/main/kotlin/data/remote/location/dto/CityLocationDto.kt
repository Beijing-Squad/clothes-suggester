package data.remote.location.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CityLocationDto(
    @SerialName("generationtime_ms")
    val generationTimeMs: Double?,
    @SerialName("results")
    val citiesCoordinates: List<CityLocationDetailsDto>? = null
)