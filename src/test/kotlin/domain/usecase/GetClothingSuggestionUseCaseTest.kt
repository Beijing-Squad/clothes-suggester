package domain.usecase

import com.google.common.truth.Truth.assertThat
import domain.repository.ClothesRepository
import helper.createClothes
import helper.createWeather
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.uuid.ExperimentalUuidApi

class GetClothingSuggestionUseCaseTest {
    private lateinit var getClothingSiggestion: GetClothingSuggestionUseCase
    private lateinit var clothesRepository: ClothesRepository

    @BeforeEach
    fun setup() {
        clothesRepository = mockk(relaxed = true)
        getClothingSiggestion = GetClothingSuggestionUseCase(clothesRepository)
    }

    @OptIn(ExperimentalUuidApi::class)
    @Test
    fun `should return list with correct cloth type based on weather`() {
        // Given
        val weather = createWeather()
        val clothes = listOf(
            createClothes(),
            createClothes(),
            createClothes()
        )
        every { getClothingSiggestion.getClothesByType(weather) } returns clothes

        // When
        val result = getClothingSiggestion.getClothesByType(weather)

        assertThat(result).isEqualTo(clothes)
    }

    @Test
    fun `should return empty list  when input cloth type is not found `() {
        // Given
        val weather = createWeather()
        every { getClothingSiggestion.getClothesByType(weather) } returns emptyList()

        // When
        val result = getClothingSiggestion.getClothesByType(weather)

        // Then
        assertThat(result).isEmpty()
    }

}