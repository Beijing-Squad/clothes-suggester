package domain.usecase

import com.google.common.truth.Truth.assertThat
import helper.createWeather
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.beijingteam.domain.entity.enums.TemperatureCategory
import org.beijingteam.domain.entity.enums.WeatherCondition
import org.beijingteam.domain.repository.WeatherRepository
import org.beijingteam.domain.usecase.GetWeatherByLongitudeAndLatitudeUseCase
import kotlin.test.BeforeTest
import kotlin.test.Test

class GetWeatherByLongitudeAndLatitudeUseCaseTest {

    private lateinit var weatherRepository: WeatherRepository
    private lateinit var getWeatherByLongitudeAndLatitudeUseCase: GetWeatherByLongitudeAndLatitudeUseCase

    @BeforeTest
    fun setup() {
        weatherRepository = mockk(relaxed = true)
        getWeatherByLongitudeAndLatitudeUseCase = GetWeatherByLongitudeAndLatitudeUseCase(weatherRepository)
    }

    @Test
    fun `should return result with weather when repository returns the weather successfully`() = runTest {
        // Given
        val longitude = 116.397
        val latitude = 39.904
        val expectedWeather = createWeather(
            temperature = 20.0,
            relativeHumidity = 50.0,
            weatherCondition = WeatherCondition.CLEAR,
            temperatureCategory = TemperatureCategory.MILD
        )
        coEvery { weatherRepository.getWeatherByLongitudeAndLatitude(longitude, latitude) } returns Result.success(
            expectedWeather
        )

        // When
        val result = getWeatherByLongitudeAndLatitudeUseCase.getWeatherByCoordinates(longitude, latitude)

        // Then
        assertThat(result).isEqualTo(Result.success(expectedWeather))
    }

    @Test
    fun `should return weather when called with maximum latitude`() = runTest {
        // Given
        val longitude = 0.0
        val latitude = 90.0
        val expectedWeather = createWeather(
            temperature = 20.0,
            relativeHumidity = 50.0,
            weatherCondition = WeatherCondition.CLEAR,
            temperatureCategory = TemperatureCategory.MILD
        )
        coEvery { weatherRepository.getWeatherByLongitudeAndLatitude(longitude, latitude) } returns Result.success(
            expectedWeather
        )

        // When
        val result = getWeatherByLongitudeAndLatitudeUseCase.getWeatherByCoordinates(longitude, latitude)

        // Then
        assertThat(result).isEqualTo(Result.success(expectedWeather))
    }

    @Test
    fun `should return weather when called with minimum latitude`() = runTest {
        // Given
        val longitude = 0.0
        val latitude = -90.0
        val expectedWeather = createWeather(
            temperature = -15.0,
            relativeHumidity = 65.0,
            weatherCondition = WeatherCondition.SNOW,
            temperatureCategory = TemperatureCategory.FREEZING
        )

        coEvery { weatherRepository.getWeatherByLongitudeAndLatitude(longitude, latitude) } returns Result.success(
            expectedWeather
        )

        // When
        val result = getWeatherByLongitudeAndLatitudeUseCase.getWeatherByCoordinates(longitude, latitude)

        // Then
        assertThat(result).isEqualTo(Result.success(expectedWeather))
    }

    @Test
    fun `should return weather when called with maximum longitude`() = runTest {
        // Given
        val longitude = 180.0
        val latitude = 0.0
        val expectedWeather = createWeather(
            temperature = 25.0,
            relativeHumidity = 60.0,
            weatherCondition = WeatherCondition.PARTLY_CLOUDY,
            temperatureCategory = TemperatureCategory.WARM
        )
        coEvery { weatherRepository.getWeatherByLongitudeAndLatitude(longitude, latitude) } returns Result.success(
            expectedWeather
        )

        // When
        val result = getWeatherByLongitudeAndLatitudeUseCase.getWeatherByCoordinates(longitude, latitude)

        // Then
        assertThat(result).isEqualTo(Result.success(expectedWeather))
    }

    @Test
    fun `should return weather when called with minimum longitude`() = runTest {
        // Given
        val longitude = -180.0
        val latitude = 0.0
        val expectedWeather = createWeather(
            temperature = 25.0,
            relativeHumidity = 60.0,
            weatherCondition = WeatherCondition.PARTLY_CLOUDY,
            temperatureCategory = TemperatureCategory.WARM
        )
        coEvery { weatherRepository.getWeatherByLongitudeAndLatitude(longitude, latitude) } returns Result.success(
            expectedWeather
        )

        // When
        val result = getWeatherByLongitudeAndLatitudeUseCase.getWeatherByCoordinates(longitude, latitude)

        // Then
        assertThat(result).isEqualTo(Result.success(expectedWeather))
    }

    @Test
    fun `should return failure when latitude exceeds 90 `() = runTest {
        // Given
        val longitude = 0.0
        val latitude = 91.0

        // When
        val result = getWeatherByLongitudeAndLatitudeUseCase.getWeatherByCoordinates(longitude, latitude)

        // Then
        assertThat(result.isFailure).isTrue()
    }

    @Test
    fun `should return failure when longitude exceeds 180`() = runTest {
        // Given
        val longitude = 190.0
        val latitude = 0.0

        // When
        val result = getWeatherByLongitudeAndLatitudeUseCase.getWeatherByCoordinates(longitude, latitude)

        // Then
        assertThat(result.isFailure).isTrue()
    }
}