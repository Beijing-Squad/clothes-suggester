package data.remote.weather.repository

import com.google.common.truth.Truth.assertThat
import domain.entity.LocationCoordinate
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.beijingteam.data.remote.weather.datasource.WeatherRemoteDataSource
import org.beijingteam.data.remote.weather.dto.WeatherDto
import org.beijingteam.data.remote.weather.repository.WeatherRepositoryImpl
import org.beijingteam.data.remote.weather.repository.mapper.WeatherMapper
import org.beijingteam.domain.type.TemperatureCategory
import org.beijingteam.domain.entity.Weather
import org.beijingteam.domain.type.WeatherCondition
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test

class WeatherRepositoryImplTest {
    private lateinit var remoteDataSource: WeatherRemoteDataSource
    private lateinit var weatherMapper: WeatherMapper
    private lateinit var weatherRepository: WeatherRepositoryImpl

    @BeforeEach
    fun setUp() {
        remoteDataSource = mockk()
        weatherMapper = mockk()
        weatherRepository = WeatherRepositoryImpl(remoteDataSource, weatherMapper)
    }

    @Test
    fun `getWeatherByCoordinate should return mapped Weather`() = runTest {
        // Given
        val coordinate = LocationCoordinate(39.9042, 116.4074)
        val remoteWeather = mockk<WeatherDto>()
        val expectedWeather = Weather(
            temperature = 25.0,
            weatherCondition = WeatherCondition.CLEAR,
            temperatureCategory = TemperatureCategory.MILD
        )
        coEvery { remoteDataSource.getWeatherByCoordinate(coordinate) } returns remoteWeather
        coEvery { weatherMapper.mapWeatherDtoToWeatherDomain(remoteWeather) } returns expectedWeather

        // When
        val result = weatherRepository.getWeatherByCoordinate(coordinate)

        // Then
        assertThat(result).isEqualTo(expectedWeather)
    }

    @Test
    fun `getWeatherByCoordinate should propagate exception from remoteDataSource`() = runTest {
        // Given
        val coordinate = LocationCoordinate(0.0, 0.0)
        coEvery { remoteDataSource.getWeatherByCoordinate(coordinate) } throws RuntimeException("Network error")

        // When & Then
        val exception = kotlin.test.assertFailsWith<RuntimeException> {
            weatherRepository.getWeatherByCoordinate(coordinate)
        }
        assertThat(exception.message).isEqualTo("Network error")
    }

    @Test
    fun `getWeatherByCoordinate should propagate exception from mapper`() = runTest {
        // Given
        val coordinate = LocationCoordinate(1.0, 1.0)
        val remoteWeather = mockk<WeatherDto>()
        coEvery { remoteDataSource.getWeatherByCoordinate(coordinate) } returns remoteWeather
        coEvery { weatherMapper.mapWeatherDtoToWeatherDomain(remoteWeather) } throws IllegalStateException("Invalid data")

        // When & Then
        val exception = kotlin.test.assertFailsWith<IllegalStateException> {
            weatherRepository.getWeatherByCoordinate(coordinate)
        }
        assertThat(exception.message).isEqualTo("Invalid data")
    }
}