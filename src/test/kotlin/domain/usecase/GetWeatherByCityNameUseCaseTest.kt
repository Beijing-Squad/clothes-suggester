import com.google.common.truth.Truth.assertThat
import domain.entity.LocationCoordinate
import domain.exception.MissingTemperatureException
import domain.exception.MissingWeatherConditionException
import domain.repository.LocationRepository
import domain.usecase.GetWeatherByCityNameUseCase
import helper.createWeather
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.beijingteam.domain.repository.WeatherRepository
import org.beijingteam.domain.type.TemperatureCategory
import org.beijingteam.domain.type.WeatherCondition
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertFailsWith

class GetWeatherByCityNameUseCaseTest {

    private lateinit var weatherRepository: WeatherRepository
    private lateinit var locationRepository: LocationRepository
    private lateinit var useCase: GetWeatherByCityNameUseCase

    @BeforeTest
    fun setup() {
        weatherRepository = mockk(relaxed = true)
        locationRepository = mockk(relaxed = true)
        useCase = GetWeatherByCityNameUseCase(weatherRepository, locationRepository)
    }

    @Test
    fun `should return weather when repository returns weather successfully`() = runTest {
        // Given
        val cityName = "Beijing"
        val coordinate = LocationCoordinate(39.904, 116.397)
        val expectedWeather = createWeather(20.0, WeatherCondition.CLEAR, TemperatureCategory.MILD)

        coEvery { locationRepository.getCoordinateByCityName(cityName) } returns coordinate
        coEvery { weatherRepository.getWeatherByCoordinate(coordinate) } returns expectedWeather

        // When
        val result = useCase.getWeatherByCityName(cityName)

        // Then
        assertThat(result).isEqualTo(expectedWeather)
    }

    @Test
    fun `should return weather for maximum latitude`() = runTest {
        // Given
        val cityName = "NorthPole"
        val coordinate = LocationCoordinate(90.0, 0.0)
        val expectedWeather = createWeather(20.0, WeatherCondition.CLEAR, TemperatureCategory.MILD)

        coEvery { locationRepository.getCoordinateByCityName(cityName) } returns coordinate
        coEvery { weatherRepository.getWeatherByCoordinate(coordinate) } returns expectedWeather

        // When
        val result = useCase.getWeatherByCityName(cityName)

        // Then
        assertThat(result).isEqualTo(expectedWeather)
    }

    @Test
    fun `should return weather for minimum latitude`() = runTest {
        // Given
        val cityName = "EquatorCity"
        val coordinate = LocationCoordinate(0.0, -90.0)
        val expectedWeather = createWeather(-15.0, WeatherCondition.SNOW, TemperatureCategory.FREEZING)

        coEvery { locationRepository.getCoordinateByCityName(cityName) } returns coordinate
        coEvery { weatherRepository.getWeatherByCoordinate(coordinate) } returns expectedWeather

        // When
        val result = useCase.getWeatherByCityName(cityName)

        // Then
        assertThat(result).isEqualTo(expectedWeather)
    }

    @Test
    fun `should return weather for maximum longitude`() = runTest {
        // Given
        val cityName = "FarEast"
        val coordinate = LocationCoordinate(0.0, 180.0)
        val expectedWeather = createWeather(25.0, WeatherCondition.PARTLY_CLOUDY, TemperatureCategory.WARM)

        coEvery { locationRepository.getCoordinateByCityName(cityName) } returns coordinate
        coEvery { weatherRepository.getWeatherByCoordinate(coordinate) } returns expectedWeather

        // When
        val result = useCase.getWeatherByCityName(cityName)

        // Then
        assertThat(result).isEqualTo(expectedWeather)
    }

    @Test
    fun `should return weather for minimum longitude`() = runTest {
        // Given
        val cityName = "FarWest"
        val coordinate = LocationCoordinate(0.0, -180.0)
        val expectedWeather = createWeather(25.0, WeatherCondition.PARTLY_CLOUDY, TemperatureCategory.WARM)

        coEvery { locationRepository.getCoordinateByCityName(cityName) } returns coordinate
        coEvery { weatherRepository.getWeatherByCoordinate(coordinate) } returns expectedWeather

        // When
        val result = useCase.getWeatherByCityName(cityName)

        // Then
        assertThat(result).isEqualTo(expectedWeather)
    }

    @Test
    fun `should throw MissingTemperatureException when temperature is missing`() = runTest {
        // Given
        val cityName = "MysteryCity"
        val coordinate = LocationCoordinate(45.0, 90.0)

        coEvery { locationRepository.getCoordinateByCityName(cityName) } returns coordinate
        coEvery { weatherRepository.getWeatherByCoordinate(coordinate) } throws MissingTemperatureException()

        // When && Then
        assertFailsWith<MissingTemperatureException> {
            useCase.getWeatherByCityName(cityName)
        }
    }

    @Test
    fun `should throw MissingWeatherConditionException when condition is missing`() = runTest {
        // Given
        val cityName = "WeatherlessCity"
        val coordinate = LocationCoordinate(45.0, 90.0)

        coEvery { locationRepository.getCoordinateByCityName(cityName) } returns coordinate
        coEvery { weatherRepository.getWeatherByCoordinate(coordinate) } throws MissingWeatherConditionException()

        // When && Then
        assertFailsWith<MissingWeatherConditionException> {
            useCase.getWeatherByCityName(cityName)
        }
    }
}
