package domain.usecase

import com.google.common.truth.Truth.assertThat
import helper.createWeather
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.beijingteam.domain.entity.enums.TemperatureCategory
import org.beijingteam.domain.entity.enums.WeatherCondition
import org.beijingteam.domain.repository.WeatherRepository
import org.beijingteam.domain.usecase.GetWeatherByCityNameUseCase
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import kotlin.test.BeforeTest
import kotlin.test.Test

class GetWeatherByCityNameUseCaseTest {

    private lateinit var weatherRepository: WeatherRepository
    private lateinit var getWeatherByCityName: GetWeatherByCityNameUseCase

    @BeforeTest
    fun setup() {
        weatherRepository = mockk(relaxed = true)
        getWeatherByCityName = GetWeatherByCityNameUseCase(weatherRepository)
    }

    @Test
    fun `should return the current weather when city name is valid`() = runTest {
        // Given
        val cityName = "Beijing"
        val expectedWeather = createWeather(
            temperature = 25.0,
            relativeHumidity = 60.0,
            weatherCondition = WeatherCondition.CLEAR,
            temperatureCategory = TemperatureCategory.MILD
        )
        coEvery {
            weatherRepository.getWeatherByCityName(cityName)
        } returns Result.success(expectedWeather)

        // When
        val result = getWeatherByCityName.getWeatherByCityName(cityName)

        // Then
        assertThat(result.getOrNull()).isEqualTo(expectedWeather)
    }

    @ParameterizedTest
    @CsvSource(
        "InvalidCity",
        "@#!#%",
        "12  34   ",
    )
    fun `should return failure when city name is invalid`(cityName: String) = runTest {
        // Given
        val exception = Throwable("Invalid City Name")
        coEvery {
            weatherRepository.getWeatherByCityName(cityName)
        } returns Result.failure(exception)

        // When
        val result = getWeatherByCityName.getWeatherByCityName(cityName)

        // Then
        assertThat(result.exceptionOrNull()).isEqualTo(exception)
    }

    @Test
    fun `should return failure when city name is empty`() = runTest {
        // Given
        val cityName = ""
        val exception = Throwable("City name cannot be empty")
        coEvery {
            weatherRepository.getWeatherByCityName(cityName)
        } returns Result.failure(exception)

        // When
        val result = getWeatherByCityName.getWeatherByCityName(cityName)

        // Then
        assertThat(result.exceptionOrNull()).isEqualTo(exception)
    }

}