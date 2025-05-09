package data.remote.location.repository

import com.google.common.truth.Truth.assertThat
import data.remote.location.datasource.LocationRemoteDataSource
import data.remote.location.dto.CityLocationDto
import data.remote.location.repository.mapper.CityLocationMapper
import domain.entity.LocationCoordinate
import domain.exception.MissingLocationException
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test

class LocationRepositoryImplTest {

    private lateinit var remoteDataSource: LocationRemoteDataSource
    private lateinit var cityLocationMapper: CityLocationMapper
    private lateinit var locationRepository: LocationRepositoryImpl


    @BeforeEach
    fun setUp() {
        remoteDataSource = mockk()
        cityLocationMapper = mockk()
        locationRepository = LocationRepositoryImpl(remoteDataSource, cityLocationMapper)
    }

    @Test
    fun `getCoordinateByCityName should return mapped LocationCoordinate`() = runTest {
        // Given
        val cityName = "Beijing"
        val dto = mockk<CityLocationDto>()
        val expectedLocation = LocationCoordinate(39.9042, 116.4074)

        coEvery { remoteDataSource.getLocationByCityName(cityName) } returns dto
        every { cityLocationMapper.mapDtoToLocationCoordinate(dto) } returns expectedLocation

        // When
        val result = locationRepository.getCoordinateByCityName(cityName)

        // Then
        assertThat(result).isEqualTo(expectedLocation)
    }

    @Test
    fun `getCoordinateByCityName should propagate exception from mapper`() = runTest {
        // Given
        val cityName = "ErrorCity"
        val dto = mockk<CityLocationDto>()

        coEvery { remoteDataSource.getLocationByCityName(cityName) } returns dto
        every { cityLocationMapper.mapDtoToLocationCoordinate(dto) } throws MissingLocationException()

        // When && Then
        assertThrows<MissingLocationException> {
            locationRepository.getCoordinateByCityName(cityName)
        }
    }

}