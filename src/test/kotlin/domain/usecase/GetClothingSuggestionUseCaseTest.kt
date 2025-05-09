package domain.usecase

import com.google.common.truth.Truth.assertThat
import domain.entity.ClothType
import domain.entity.Clothes
import helper.createClothes
import io.mockk.every
import io.mockk.mockk
import org.beijingteam.data.local.cloth.repository.ClothesRepositoryImpl
import org.beijingteam.domain.entity.TemperatureCategory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.uuid.ExperimentalUuidApi

class GetClothingSuggestionUseCaseTest {
    private lateinit var getClothes: GetClothingSuggestionUseCase
    private lateinit var clothesRepo: ClothesRepositoryImpl

    @BeforeEach
    fun setup() {
        clothesRepo = mockk(relaxed = true)
        getClothes = GetClothingSuggestionUseCase(clothesRepo)

    }

    @OptIn(ExperimentalUuidApi::class)
    @Test
    fun `should return clothes list with heavy cloth type  when temperature is freezing `() {
        // Given
        val clothType = ClothType.HEAVY_CLOTH
        val tempCategory = TemperatureCategory.FREEZING
        val matchedClothes = listOf(createClothes(clothType = ClothType.HEAVY_CLOTH))
        every { clothesRepo.getClothByType(clothType) } returns matchedClothes

        // When
        val result = getClothes.getClothByTempCategory(tempCategory)

        //Then
        assertThat(result).containsExactlyElementsIn(matchedClothes)
    }

    @OptIn(ExperimentalUuidApi::class)
    @Test
    fun `should return clothes list with medium cloth type  when temperature is COOL`() {
        // Given
        val clothType = ClothType.MEDIUM_CLOTH
        val tempCategory = TemperatureCategory.COOL
        val matchedClothes = listOf(createClothes(clothType = ClothType.MEDIUM_CLOTH))
        every { clothesRepo.getClothByType(clothType) } returns matchedClothes

        // When
        val result = getClothes.getClothByTempCategory(tempCategory)

        //Then
        assertThat(result).containsExactlyElementsIn(matchedClothes)
    }

    @OptIn(ExperimentalUuidApi::class)
    @Test
    fun `should return clothes list with light cloth type  when temperature is HOT`() {
        // Given
        val clothType = ClothType.LIGHT_CLOTH
        val tempCategory = TemperatureCategory.HOT
        val matchedClothes = listOf(createClothes(clothType = ClothType.LIGHT_CLOTH))
        every { clothesRepo.getClothByType(clothType) } returns matchedClothes

        // When
        val result = getClothes.getClothByTempCategory(tempCategory)

        //Then
        assertThat(result).containsExactlyElementsIn(matchedClothes)
    }


    @OptIn(ExperimentalUuidApi::class)
    @Test
    fun `should return empty list when datasource is empty `() {
        // Given
        val clothType = ClothType.LIGHT_CLOTH
        val tempCategory = TemperatureCategory.HOT
        val matchedClothes = listOf<Clothes>()
        every { clothesRepo.getClothByType(clothType) } returns matchedClothes

        // When
        val result = getClothes.getClothByTempCategory(tempCategory)

        //Then
        assertThat(result).containsExactlyElementsIn(matchedClothes)
    }



}