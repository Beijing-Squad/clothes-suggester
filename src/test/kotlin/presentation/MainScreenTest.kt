package presentation

import domain.entity.ClothType
import domain.entity.LocationCoordinate
import domain.exception.MissingLocationException
import domain.repository.LocationRepository
import domain.usecase.GetCoordinateByCityNameUseCase
import domain.usecase.GetWeatherByLongitudeAndLatitudeUseCase
import helper.createClothes
import helper.createWeather
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.beijingteam.domain.repository.WeatherRepository
import org.beijingteam.domain.usecase.GetClothingSuggestionUseCase
import org.beijingteam.presentation.MainScreen
import org.beijingteam.presentation.consoleIO.ConsoleIO
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.uuid.ExperimentalUuidApi

class MainScreenTest {
    private lateinit var consoleIO: ConsoleIO
    private lateinit var mainScreen: MainScreen
    private lateinit var getWeatherByLongitudeAndLatitudeUseCase: GetWeatherByLongitudeAndLatitudeUseCase
    private lateinit var geCoorinateByCityNameUseCase: GetCoordinateByCityNameUseCase
    private lateinit var getClothingSuggestionUseCase: GetClothingSuggestionUseCase
    private lateinit var locationRepository: LocationRepository
    private lateinit var weatherRepository: WeatherRepository

    @BeforeEach
    fun setup() {
        consoleIO = mockk(relaxed = true)
        geCoorinateByCityNameUseCase = mockk(relaxed = true)
        getWeatherByLongitudeAndLatitudeUseCase = mockk(relaxed = true)
        weatherRepository = mockk(relaxed = true)
        getClothingSuggestionUseCase=mockk(relaxed = true)
        locationRepository=mockk(relaxed = true)
        mainScreen = MainScreen(
            geCoorinateByCityNameUseCase,
            getWeatherByLongitudeAndLatitudeUseCase,
            getClothingSuggestionUseCase,
            consoleIO
        )
    }

    @Test
    fun `should show message welcome and call run input loop fun when start app`() {
        // When
        mainScreen.start()

        // Then
        verify (exactly = 2){
            consoleIO.showWithLine(any())
        }
    }

    @Test
    fun `should show invalid city name message when enter empty name`() = runTest {
        // Given
        val cityName = " "
        every { consoleIO.read() } returns cityName andThen EXIT_INPUT

        // When
        mainScreen.start()

        // Then
        verify {
            consoleIO.showWithLine("invalid city name")
        }
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
    fun `should return weather and clothes when city name is valid`() =runTest{
        // Given
        val cityName = "Baghdad"
        val dummyWeather = createWeather()
        val heavyClothes= createClothes()
        val mediumClothes=createClothes(clothType = ClothType.MEDIUM_CLOTH)
        val clothType= ClothType.MEDIUM_CLOTH
        val locationCoordinate= LocationCoordinate(
            latitude = 31.0,
            longitude = 23.0
        )
        every { consoleIO.read() } returns  cityName andThen EXIT_INPUT
        coEvery { locationRepository.getCoordinateByCityName(cityName) } returns locationCoordinate
        coEvery { weatherRepository.getWeatherByCoordinate(locationCoordinate) } returns dummyWeather
        every{ getClothingSuggestionUseCase.getClothByType(clothType) } returns listOf(
           heavyClothes,mediumClothes
        )

        // When
        mainScreen.start()
        val location = geCoorinateByCityNameUseCase.getCoordinateByCityName(cityName)
        val weather = getWeatherByLongitudeAndLatitudeUseCase.getWeatherByCoordinates(location)
        val cloth=getClothingSuggestionUseCase.getClothByType(clothType)

        // Then
        assertEquals(dummyWeather, weather)
        assertEquals(location,locationCoordinate)
        assertEquals(cloth,listOf(mediumClothes))

    }

    @Test
    fun `should show error message when weather fetching fails`() =runTest{
        // Given
        val cityName = "qwerty"
        every { consoleIO.read() } returns cityName andThen EXIT_INPUT
        coEvery { locationRepository.getCoordinateByCityName(cityName) } throws MissingLocationException()

        // When
        mainScreen.start()
        geCoorinateByCityNameUseCase.getCoordinateByCityName(cityName)

        // Then
        verify {
            consoleIO.showWithLine(any())
        }
    }


    private companion object {
        const val EXIT_INPUT = "Exit"

    }

}