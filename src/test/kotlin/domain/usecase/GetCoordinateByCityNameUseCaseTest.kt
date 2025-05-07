package domain.usecase

import com.google.common.truth.Truth.assertThat
import domain.entity.LocationCoordinate
import domain.exception.MissingLocationException
import domain.repository.LocationRepository
import domain.useCase.GetCoordinateByCityNameUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import kotlin.test.BeforeTest
import kotlin.test.Test

class GetCoordinateByCityNameUseCaseTest {

    private lateinit var locationRepository: LocationRepository
    private lateinit var getCoordinateByCityName: GetCoordinateByCityNameUseCase

    @BeforeTest
    fun setup() {
        locationRepository = mockk(relaxed = true)
        getCoordinateByCityName = GetCoordinateByCityNameUseCase(locationRepository)
    }

    @Test
    fun `should return coordinates when city name is found`() = runTest {
        // Given
        val cityName = "Beijing"
        val expectedCoordinate = LocationCoordinate(39.9042, 116.4074)

        coEvery {
            locationRepository.getCoordinateByCityName(cityName)
        } returns expectedCoordinate

        // When
        val result = getCoordinateByCityName.getCoordinateByCityName(cityName)

        // Then
        assertThat(result).isEqualTo(expectedCoordinate)
    }

    @Test
    fun `should return coordinates when city name is in different case`() = runTest {
        // Given
        val cityName = "BeIjInG"
        val expectedCoordinate = LocationCoordinate(39.9042, 116.4074)

        coEvery {
            locationRepository.getCoordinateByCityName(cityName)
        } returns expectedCoordinate

        // When
        val result = getCoordinateByCityName.getCoordinateByCityName(cityName)

        // Then
        assertThat(result).isEqualTo(expectedCoordinate)
    }

    @ParameterizedTest
    @CsvSource(
        "InvalidCity",
        "@#!#%",
        "12  34   ",
        "New@York!"
    )
    fun `should throw MissingLocationException when city name is not found`(cityName: String) = runTest {
        // Given
        coEvery {
            locationRepository.getCoordinateByCityName(cityName)
        } throws MissingLocationException()

        // When && Then
        assertThrows<MissingLocationException> {
            getCoordinateByCityName.getCoordinateByCityName(cityName)
        }

    }

    @Test
    fun `should throw MissingLocationException when city name is empty`() = runTest {
        // Given
        val cityName = ""
        coEvery {
            locationRepository.getCoordinateByCityName(cityName)
        } throws MissingLocationException()

        // When && Then
        assertThrows<MissingLocationException> {
            getCoordinateByCityName.getCoordinateByCityName(cityName)
        }
    }

}