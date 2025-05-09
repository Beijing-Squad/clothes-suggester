import domain.entity.ClothType
import domain.entity.Clothes
import domain.exception.MissingLocationException
import domain.usecase.GetClothingSuggestionUseCase
import domain.usecase.GetWeatherByCityNameUseCase
import helper.createWeather
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.beijingteam.presentation.MainScreen
import org.beijingteam.presentation.consoleIO.ConsoleIO
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test

@OptIn(kotlin.uuid.ExperimentalUuidApi::class)
class MainScreenTest {

    private lateinit var consoleIO: ConsoleIO
    private lateinit var mainScreen: MainScreen
    private lateinit var getWeatherByCityNameUseCase: GetWeatherByCityNameUseCase
    private lateinit var getClothingSuggestionUseCase: GetClothingSuggestionUseCase

    @BeforeEach
    fun setup() {
        consoleIO = mockk(relaxed = true)

        getWeatherByCityNameUseCase = mockk(relaxed = true)
        getClothingSuggestionUseCase = mockk(relaxed = true)

        mainScreen = MainScreen(
            getWeatherByCityNameUseCase,
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
        verify { consoleIO.showWithLine(any()) }
    }

    @Test
    fun `should return weather and clothes when city name is valid`() = runTest {
        // Given
        val cityName = "Baghdad"
        val dummyWeather = createWeather()
        val clothes = listOf(
            Clothes(clothName = "Hoodie", clothType = ClothType.MEDIUM_CLOTH),
            Clothes(clothName = "Light Jacket", clothType = ClothType.MEDIUM_CLOTH),
            Clothes(clothName = "Pullover", clothType = ClothType.MEDIUM_CLOTH),
            Clothes(clothName = "Long Sleeve Top", clothType = ClothType.MEDIUM_CLOTH),
            Clothes(clothName = "Jeans", clothType = ClothType.MEDIUM_CLOTH),
            Clothes(clothName = "Cargo Pants", clothType = ClothType.MEDIUM_CLOTH),
        )
        every { consoleIO.read() } returns cityName andThen EXIT_INPUT
        coEvery { getWeatherByCityNameUseCase.getWeatherByCityName(cityName) } returns dummyWeather
        coEvery { getClothingSuggestionUseCase.getClothesByType(dummyWeather) } returns clothes

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
        coEvery { getWeatherByCityNameUseCase.getWeatherByCityName(cityName) } throws MissingLocationException()

        // When
        mainScreen.start()

        // Then
        verify { consoleIO.showWithLine(any()) }
    }

    private companion object {
        const val EXIT_INPUT = "Exit"
    }
}
