package data.remote.location.datasource

import io.ktor.client.*
import data.remote.location.dto.CityLocationDto
import org.beijingteam.data.remote.utils.ApiUrls.BASE_CITY_LOCATION_URL
import org.beijingteam.data.remote.utils.fetchWithQueryParams

class LocationRemoteDataSourceImpl(
    private val client: HttpClient,
): LocationRemoteDataSource {
    override suspend fun getLocationByCityName(cityName: String): CityLocationDto {
        val locationQueryParams = mapOf("name" to cityName)
        return client.fetchWithQueryParams<CityLocationDto>(BASE_CITY_LOCATION_URL, locationQueryParams)
    }
}