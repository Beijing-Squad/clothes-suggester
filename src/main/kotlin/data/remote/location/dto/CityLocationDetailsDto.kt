package data.remote.location.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CityLocationDetailsDto(
    @SerialName("id")
    val id: Int?,
    @SerialName("name")
    val cityName: String?,
    @SerialName("latitude")
    val latitude: Double?,
    @SerialName("longitude")
    val longitude: Double?,
    @SerialName("elevation")
    val elevation: Double?,
    @SerialName("feature_code")
    val featureCode: String?,
    @SerialName("timezone")
    val timezone: String?,
    @SerialName("population")
    val population: Int?,
    @SerialName("country_id")
    val countryId: Int?,
    @SerialName("country")
    val country: String?,
    @SerialName("country_code")
    val countryCode: String?
)