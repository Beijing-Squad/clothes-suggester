package data.remote.location.datasource

import io.ktor.client.*
import kotlinx.serialization.json.Json
import data.remote.location.dto.CityLocationDto
import org.beijingteam.data.remote.utils.ApiUrls.BASE_CITY_LOCATION_URL
import org.beijingteam.data.remote.utils.getWithParams

class LocationRemoteDataSourceImpl(
    private val client: HttpClient,
    private val json: Json
): LocationRemoteDataSource {
    override suspend fun getLocationByCityName(cityName: String): CityLocationDto {
        return client.getWithParams<CityLocationDto>(BASE_CITY_LOCATION_URL, mapOf("name" to cityName), json)
    }
}