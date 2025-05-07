package org.beijingteam.data.weather.datasource

import data.weather.datasource.LocationRemoteDataSource
import data.weather.dto.CityLocationDto
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.serialization.json.Json
import org.beijingteam.utils.ApiUrls.BASE_CITY_LOCATION_URL

class LocationRemoteDataSourceImpl(
    private val client: HttpClient,
    private val json: Json
): LocationRemoteDataSource {
    override suspend fun getLocationByCityName(cityName: String): CityLocationDto {
        val response = client.get(BASE_CITY_LOCATION_URL){
            parameter("name", cityName)
        }
        return json.decodeFromString<CityLocationDto>(response.bodyAsText())
    }
}