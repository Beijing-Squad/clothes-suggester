package data.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class GeocodingApiResponse(
    val results: List<GeoLocationModel>? = null
)