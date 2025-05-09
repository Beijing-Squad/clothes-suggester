package data.remote.location.repository.mapper

import CityLocationDetailsDto
import com.google.common.truth.Truth.assertThat
import data.remote.location.dto.CityLocationDto
import domain.entity.LocationCoordinate
import domain.exception.MissingLocationException
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test

class CityLocationMapperTest {

    private lateinit var mapper: CityLocationMapper

    @BeforeEach
    fun setup() {
        mapper = CityLocationMapper()
    }

    @Test
    fun `mapDtoToLocationCoordinate should return LocationCoordinate when data is valid`() {
        // Given
        val dto = CityLocationDto(
            generationTimeMs = 1.0,
            citiesCoordinates = listOf(
                CityLocationDetailsDto(
                    latitude = 48.8566,
                    longitude = 2.3522
                )
            )
        )

        // When
        val result = mapper.mapDtoToLocationCoordinate(dto)

        // Then
        assertThat(result).isEqualTo(LocationCoordinate(48.8566, 2.3522))
    }

    @Test
    fun `mapDtoToLocationCoordinate should throw MissingLocationException when citiesCoordinates is null`() {
        // Given
        val dto = CityLocationDto(
            generationTimeMs = 1.0,
            citiesCoordinates = null
        )

        // When && Then
        assertThrows<MissingLocationException> {
            mapper.mapDtoToLocationCoordinate(dto)
        }
    }

    @Test
    fun `mapDtoToLocationCoordinate should throw MissingLocationException when citiesCoordinates is empty`() {
        // Given
        val dto = CityLocationDto(
            generationTimeMs = 1.0,
            citiesCoordinates = emptyList()
        )

        // When && Then
        assertThrows<MissingLocationException> {
            mapper.mapDtoToLocationCoordinate(dto)
        }
    }

    @Test
    fun `mapDtoToLocationCoordinate should throw MissingLocationException when latitude is null`() {
        // Given
        val dto = CityLocationDto(
            generationTimeMs = 1.0,
            citiesCoordinates = listOf(
                CityLocationDetailsDto(
                    latitude = null,
                    longitude = 10.0
                )
            )
        )

        // When && Then
        assertThrows<MissingLocationException> {
            mapper.mapDtoToLocationCoordinate(dto)
        }
    }

    @Test
    fun `mapDtoToLocationCoordinate should throw MissingLocationException when longitude is null`() {
        // Given
        val dto = CityLocationDto(
            generationTimeMs = 1.0,
            citiesCoordinates = listOf(
                CityLocationDetailsDto(
                    latitude = 20.0,
                    longitude = null
                )
            )
        )

        // When && Then
        assertThrows<MissingLocationException> {
            mapper.mapDtoToLocationCoordinate(dto)
        }
    }

}