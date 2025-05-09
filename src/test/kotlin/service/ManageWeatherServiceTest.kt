package service

import com.google.common.truth.Truth.assertThat
import domain.entity.ClothType
import domain.entity.Clothes
import domain.entity.LocationCoordinate
import domain.usecase.GetClothingSuggestionUseCase
import domain.usecase.GetCoordinateByCityNameUseCase
import domain.usecase.GetWeatherByLongitudeAndLatitudeUseCase
import io.mockk.coEvery
import io.mockk.coVerifyOrder
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.beijingteam.domain.entity.TemperatureCategory
import org.beijingteam.domain.entity.Weather
import org.beijingteam.domain.entity.WeatherCondition
import org.beijingteam.service.ManageWeatherService
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test

@OptIn(kotlin.uuid.ExperimentalUuidApi::class)
class ManageWeatherServiceTest {

    private lateinit var getCoordinateByCityName: GetCoordinateByCityNameUseCase
    private lateinit var getWeatherByCoordinate: GetWeatherByLongitudeAndLatitudeUseCase
    private lateinit var clothingSuggestion: GetClothingSuggestionUseCase
    private lateinit var manageWeatherService: ManageWeatherService

    @BeforeEach
    fun setUp() {
        getCoordinateByCityName = mockk()
        getWeatherByCoordinate = mockk()
        clothingSuggestion = mockk()
        manageWeatherService = ManageWeatherService(
            getCoordinateByCityName,
            getWeatherByCoordinate,
            clothingSuggestion
        )
    }

    @Test
    fun `should execute weather flow and return weather and clothes`() = runTest {
        // Given
        val cityName = "Beijing"
        val coordinates = LocationCoordinate(latitude = 39.904, longitude = 116.407)
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

        coEvery { getCoordinateByCityName.getCoordinateByCityName(cityName) } returns coordinates
        coEvery { getWeatherByCoordinate.getWeatherByCoordinates(coordinates) } returns weather
        coEvery { clothingSuggestion.getClothByType(ClothType.MEDIUM_CLOTH) } returns clothes

        // When
        val result = manageWeatherService.executeWeatherFlow(cityName)

        // Then
        assertThat(result).isEqualTo(Pair(weather, clothes))
        coVerifyOrder {
            getCoordinateByCityName.getCoordinateByCityName(cityName)
            getWeatherByCoordinate.getWeatherByCoordinates(coordinates)
            clothingSuggestion.getClothByType(ClothType.MEDIUM_CLOTH)
        }
    }

}