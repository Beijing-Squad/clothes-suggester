package data.remote.location.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CityLocationDetailsDto(
    @SerialName("id")
    val id: Int? = null,
    @SerialName("name")
    val cityName: String? = null,
    @SerialName("latitude")
    val latitude: Double? = null,
    @SerialName("longitude")
    val longitude: Double? = null,
    @SerialName("elevation")
    val elevation: Double? = null,
    @SerialName("feature_code")
    val featureCode: String? = null,
    @SerialName("timezone")
    val timezone: String? = null,
    @SerialName("population")
    val population: Int? = null,
    @SerialName("country_id")
    val countryId: Int? = null,
    @SerialName("country")
    val country: String? = null,
    @SerialName("country_code")
    val countryCode: String? = null
)