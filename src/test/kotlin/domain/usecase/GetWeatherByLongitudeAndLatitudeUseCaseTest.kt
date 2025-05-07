package domain.usecase

import com.google.common.truth.Truth.assertThat
import domain.entity.LocationCoordinate
import helper.createWeather
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.beijingteam.domain.entity.TemperatureCategory
import org.beijingteam.domain.entity.WeatherCondition
import org.beijingteam.domain.repository.WeatherRepository
import domain.useCase.GetWeatherByLongitudeAndLatitudeUseCase
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertFailsWith

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
        val locationCoordinate = LocationCoordinate(latitude = 39.904, longitude = 116.397)
        val expectedWeather = createWeather(
            temperature = 20.0,
            weatherCondition = WeatherCondition.CLEAR,
            temperatureCategory = TemperatureCategory.MILD
        )
        coEvery { weatherRepository.getWeatherByCoordinate(locationCoordinate) } returns
                expectedWeather


        // When
        val result = getWeatherByLongitudeAndLatitudeUseCase.getWeatherByCoordinates(locationCoordinate)

        // Then
        assertThat(result).isEqualTo(expectedWeather)
    }

    @Test
    fun `should return weather when called with maximum latitude`() = runTest {
        // Given
        val locationCoordinate = LocationCoordinate(latitude = 90.0, longitude = 0.0)
        val expectedWeather = createWeather(
            temperature = 20.0,
            weatherCondition = WeatherCondition.CLEAR,
            temperatureCategory = TemperatureCategory.MILD
        )
        coEvery { weatherRepository.getWeatherByCoordinate(locationCoordinate) } returns
                expectedWeather


        // When
        val result = getWeatherByLongitudeAndLatitudeUseCase.getWeatherByCoordinates(locationCoordinate)

        // Then
        assertThat(result).isEqualTo(Result.success(expectedWeather))
    }

    @Test
    fun `should return weather when called with minimum latitude`() = runTest {
        // Given
        val locationCoordinate = LocationCoordinate(latitude = 0.0, longitude = -90.0)
        val expectedWeather = createWeather(
            temperature = -15.0,
            weatherCondition = WeatherCondition.SNOW,
            temperatureCategory = TemperatureCategory.FREEZING
        )

        coEvery { weatherRepository.getWeatherByCoordinate(locationCoordinate) } returns
                expectedWeather


        // When
        val result = getWeatherByLongitudeAndLatitudeUseCase.getWeatherByCoordinates(locationCoordinate)

        // Then
        assertThat(result).isEqualTo(expectedWeather)
    }

    @Test
    fun `should return weather when called with maximum longitude`() = runTest {
        // Given
        val locationCoordinate = LocationCoordinate(latitude = 0.0, longitude = 180.0)
        val expectedWeather = createWeather(
            temperature = 25.0,
            weatherCondition = WeatherCondition.PARTLY_CLOUDY,
            temperatureCategory = TemperatureCategory.WARM
        )
        coEvery { weatherRepository.getWeatherByCoordinate(locationCoordinate) } returns
                expectedWeather


        // When
        val result = getWeatherByLongitudeAndLatitudeUseCase.getWeatherByCoordinates(locationCoordinate)

        // Then
        assertThat(result).isEqualTo(expectedWeather)
    }

    @Test
    fun `should return weather when called with minimum longitude`() = runTest {
        // Given
        val locationCoordinate = LocationCoordinate(latitude = 0.0, longitude = -180.0)
        val expectedWeather = createWeather(
            temperature = 25.0,
            weatherCondition = WeatherCondition.PARTLY_CLOUDY,
            temperatureCategory = TemperatureCategory.WARM
        )
        coEvery { weatherRepository.getWeatherByCoordinate(locationCoordinate) } returns
                expectedWeather

        // When
        val result = getWeatherByLongitudeAndLatitudeUseCase.getWeatherByCoordinates(locationCoordinate)

        // Then
        assertThat(result).isEqualTo(expectedWeather)
    }

    @Test
    fun `should return failure when latitude exceeds 90 `() = runTest {
        // Given
        val locationCoordinate = LocationCoordinate(latitude = 91.0, longitude = 0.0)

        // Then
        assertFailsWith<IllegalArgumentException> {
            getWeatherByLongitudeAndLatitudeUseCase.getWeatherByCoordinates(locationCoordinate)
        }
    }

    @Test
    fun `should return failure when longitude exceeds 180`() = runTest {
        // Given
        val locationCoordinate = LocationCoordinate(latitude = 0.0, longitude = 190.0)

        // Then
        assertFailsWith<IllegalArgumentException> {
            getWeatherByLongitudeAndLatitudeUseCase.getWeatherByCoordinates(locationCoordinate)
        }
    }
}