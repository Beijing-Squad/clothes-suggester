package presentation

import helper.createWeather
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.beijingteam.domain.repository.WeatherRepository
import org.beijingteam.domain.usecase.GetWeatherByCityNameUseCase
import org.beijingteam.domain.usecase.GetWeatherByLongitudeAndLatitudeUseCase
import org.beijingteam.presentation.MainScreen
import org.beijingteam.presentation.consoleIO.ConsoleIO
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class MainScreenTest {
    private lateinit var consoleIO: ConsoleIO
    private lateinit var mainScreen: MainScreen
    private lateinit var getWeatherByLongitudeAndLatitudeUseCase: GetWeatherByLongitudeAndLatitudeUseCase
    private lateinit var getWeatherByCityNameUseCase: GetWeatherByCityNameUseCase
    private lateinit var weatherRepository: WeatherRepository

    @BeforeEach
    fun setup() {
        consoleIO = mockk(relaxed = true)
        getWeatherByCityNameUseCase = mockk(relaxed = true)
        getWeatherByLongitudeAndLatitudeUseCase = mockk(relaxed = true)
        weatherRepository = mockk(relaxed = true)
        mainScreen = MainScreen(
            getWeatherByCityNameUseCase,
            getWeatherByLongitudeAndLatitudeUseCase,
            consoleIO
        )
    }

    @Test
    fun `should show message welcome when start app`() {
        // When
        mainScreen.start()

        // Then
        verify {
            consoleIO.showWithLine(any())
            consoleIO.showWithLine(any())
        }
    }


    @Test
    fun `should call search by city name function when choice is yes`() {
        // Given
        val cityName = "Baghdad"
        every { consoleIO.read() } returns INPUT_CHOICE andThen cityName andThen EXIT_INPUT

        // When
        mainScreen.start()

        // Then
        verify { getWeatherByCityNameUseCase.GetWeatherByCityName(cityName) }
    }

    @Test
    fun `should exit when choice is no`() {
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
    fun `should return weather when city name is valid`() {
        // Given
        val cityName = "Baghdad"
        val dummyWeather = createWeather()
        every { consoleIO.read() } returns INPUT_CHOICE andThen cityName andThen EXIT_INPUT
        coEvery { weatherRepository.getWeatherByCityName(cityName) } returns Result.success(dummyWeather)

        // When
        mainScreen.start()
        val result = getWeatherByCityNameUseCase.GetWeatherByCityName(cityName)

        // Then
        assertTrue(result.isSuccess)
        assertEquals(dummyWeather, result.getOrNull())
    }

    @Test
    fun `should return failure when weather fetching fails`() {
        // Given
        val cityName = "Baghdad"
        val exception = Exception("City not found")
        every { consoleIO.read() } returns INPUT_CHOICE andThen cityName andThen EXIT_INPUT
        coEvery { weatherRepository.getWeatherByCityName(cityName) } returns Result.failure(exception)

        // When
        mainScreen.start()
        val result = getWeatherByCityNameUseCase.GetWeatherByCityName(cityName)

        // Then
        assertTrue(result.isFailure)
        assertEquals("City not found", result.exceptionOrNull()?.message)
    }


    private companion object {
        const val EXIT_INPUT = "no"
        const val INPUT_CHOICE = "yes"
    }

}