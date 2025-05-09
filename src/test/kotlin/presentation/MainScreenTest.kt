package presentation

import domain.entity.ClothType
import domain.entity.Clothes
import domain.exception.MissingLocationException
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.beijingteam.domain.entity.TemperatureCategory
import org.beijingteam.domain.entity.Weather
import org.beijingteam.domain.entity.WeatherCondition
import org.beijingteam.presentation.MainScreen
import org.beijingteam.presentation.consoleIO.ConsoleIO
import org.beijingteam.service.ManageWeatherService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.uuid.ExperimentalUuidApi

class MainScreenTest {

    private lateinit var mainScreen: MainScreen
    private lateinit var weatherService: ManageWeatherService
    private lateinit var consoleIO: ConsoleIO

    @BeforeEach
    fun setup() {
        consoleIO = mockk(relaxed = true)
        weatherService = mockk()
        mainScreen = MainScreen(weatherService, consoleIO)
    }

    @Test
    fun `should exit when choice is Exit`() {
        // Given
        every { consoleIO.read() } returns EXIT_INPUT

        // When
        mainScreen.start()

        // Then
        verify { consoleIO.showWithLine(any()) }
    }

    @OptIn(ExperimentalUuidApi::class)
    @Test
    fun `should return weather and clothes when city name is valid`() = runTest {
        // Given
        val cityName = "Beijing"
        val weather = Weather(
            temperature = 20.0,
            weatherCondition = WeatherCondition.CLEAR,
            temperatureCategory = TemperatureCategory.MILD
        )
        val clothes = listOf(
            Clothes(clothName = "Hoodie", clothType = ClothType.MEDIUM_CLOTH),
            Clothes(clothName = "Light Jacket", clothType = ClothType.MEDIUM_CLOTH),
            Clothes(clothName = "Pullover", clothType = ClothType.MEDIUM_CLOTH),
        )

        every { consoleIO.read() } returns cityName andThen EXIT_INPUT
        coEvery { weatherService.executeWeatherFlow(cityName) } returns Pair(weather, clothes)

        // When
        mainScreen.start()

        // Then
        verify { consoleIO.showWithLine(any()) }
    }

    @Test
    fun `should show error message when weather fetching fails`() = runTest {
        // Given
        val cityName = "qwerty"
        every { consoleIO.read() } returns cityName andThen EXIT_INPUT
        coEvery { weatherService.executeWeatherFlow(cityName) } throws MissingLocationException()

        // When & Then
        mainScreen.start()

        // Then
        verify { consoleIO.showWithLine(any()) }
    }

    private companion object {
        const val EXIT_INPUT = "Exit"

    }

}