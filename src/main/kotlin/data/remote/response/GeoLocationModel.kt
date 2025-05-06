package data.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class GeoLocationModel (
    val latitude: Double,
    val longitude: Double,
)