package presentation

import domain.exception.MissingLocationException
import domain.repository.LocationRepository
import domain.usecase.GetCoordinateByCityNameUseCase
import domain.usecase.GetWeatherByLongitudeAndLatitudeUseCase
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.beijingteam.domain.repository.WeatherRepository
import org.beijingteam.domain.useCase.GetClothingSuggestionUseCase
import org.beijingteam.presentation.MainScreen
import org.beijingteam.presentation.consoleIO.ConsoleIO
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class MainScreenTest {

    private lateinit var consoleIO: ConsoleIO
    private lateinit var mainScreen: MainScreen
    private lateinit var getWeatherByLongitudeAndLatitudeUseCase: GetWeatherByLongitudeAndLatitudeUseCase
    private lateinit var getCoordinateByCityNameUseCase: GetCoordinateByCityNameUseCase
    private lateinit var getClothingSuggestionUseCase: GetClothingSuggestionUseCase
    private lateinit var locationRepository: LocationRepository
    private lateinit var weatherRepository: WeatherRepository

    @BeforeEach
    fun setup() {
        consoleIO = mockk(relaxed = true)
        getCoordinateByCityNameUseCase = mockk(relaxed = true)
        getWeatherByLongitudeAndLatitudeUseCase = mockk(relaxed = true)
        weatherRepository = mockk(relaxed = true)
        getClothingSuggestionUseCase = mockk(relaxed = true)
        locationRepository = mockk(relaxed = true)
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

    @Test
    fun `should show error message when weather fetching fails`() = runTest {
        // Given
        val cityName = "qwerty"
        every { consoleIO.read() } returns cityName andThen EXIT_INPUT
        coEvery { locationRepository.getCoordinateByCityName(cityName) } throws MissingLocationException()

        // When
        mainScreen.start()
        getCoordinateByCityNameUseCase.getCoordinateByCityName(cityName)

        // Then
        verify {
            consoleIO.showWithLine(any())
        }
    }

    private companion object {
        const val EXIT_INPUT = "Exit"

    }

}