package presentation

import com.google.common.truth.Truth.assertThat
import data.local.clothes.datasource.ClothesDataSource
import domain.entity.LocationCoordinate
import domain.exception.MissingLocationException
import domain.repository.LocationRepository
import domain.usecase.GetClothingSuggestionUseCase
import domain.usecase.GetCoordinateByCityNameUseCase
import domain.usecase.GetWeatherByLongitudeAndLatitudeUseCase
import helper.createWeather
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import data.local.clothes.repository.ClothesRepositoryImpl
import org.beijingteam.domain.repository.WeatherRepository
import org.beijingteam.presentation.MainScreen
import org.beijingteam.presentation.consoleIO.ConsoleIO
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.uuid.ExperimentalUuidApi

class MainScreenTest {

    private lateinit var consoleIO: ConsoleIO
    private lateinit var mainScreen: MainScreen
    private lateinit var getWeatherByLongitudeAndLatitudeUseCase: GetWeatherByLongitudeAndLatitudeUseCase
    private lateinit var getCoordinateByCityNameUseCase: GetCoordinateByCityNameUseCase
    private lateinit var getClothingSuggestionUseCase: GetClothingSuggestionUseCase
    private lateinit var locationRepository: LocationRepository
    private lateinit var weatherRepository: WeatherRepository
    private lateinit var clothesDataSource: ClothesDataSource
    private lateinit var clothesRepo: ClothesRepositoryImpl

    @BeforeEach
    fun setup() {
        consoleIO = mockk(relaxed = true)
        weatherRepository = mockk(relaxed = true)
        locationRepository = mockk(relaxed = true)
        clothesDataSource = mockk(relaxed = true)
        clothesRepo = mockk(relaxed = true)
        getClothingSuggestionUseCase = GetClothingSuggestionUseCase(clothesRepo)
        getWeatherByLongitudeAndLatitudeUseCase = GetWeatherByLongitudeAndLatitudeUseCase(weatherRepository)
        getCoordinateByCityNameUseCase = GetCoordinateByCityNameUseCase(locationRepository)

        mainScreen = MainScreen(
            getWeatherByLongitudeAndLatitudeUseCase,
            getCoordinateByCityNameUseCase,
            getClothingSuggestionUseCase,
            consoleIO
        )
    }

    @Test
    fun `should exit when choice is Exit`() {
        // Given
        every { consoleIO.read() } returns EXIT_INPUT

        // When
        mainScreen.start()

        // Then
        verify {
            consoleIO.showWithLine(any())
        }
    }

    @OptIn(ExperimentalUuidApi::class)
    @Test
    fun `should return weather and clothes when city name is valid`() = runTest {
        // Given
        val cityName = "Baghdad"
        val dummyWeather = createWeather()
        val locationCoordinate = LocationCoordinate(
            latitude = 31.0,
            longitude = 23.0
        )
        every { consoleIO.read() } returns cityName andThen EXIT_INPUT
        coEvery { locationRepository.getCoordinateByCityName(cityName) } returns locationCoordinate
        coEvery { weatherRepository.getWeatherByCoordinate(locationCoordinate) } returns dummyWeather

        // When
        mainScreen.start()
        val location = getCoordinateByCityNameUseCase.getCoordinateByCityName(cityName)
        val weather = getWeatherByLongitudeAndLatitudeUseCase.getWeatherByCoordinates(location)

        // Then

        assertThat(weather).isEqualTo(dummyWeather)
        assertThat(location).isEqualTo(locationCoordinate)

    }

    @Test
    fun `should show error message when weather fetching fails`() = runTest {
        // Given
        val cityName = "qwerty"
        every { consoleIO.read() } returns cityName andThen EXIT_INPUT
        coEvery { locationRepository.getCoordinateByCityName(cityName) } throws MissingLocationException()

        // When & Then
        mainScreen.start()
        assertThrows<MissingLocationException> { getCoordinateByCityNameUseCase.getCoordinateByCityName(cityName) }

    }

    private companion object {
        const val EXIT_INPUT = "Exit"

    }

}