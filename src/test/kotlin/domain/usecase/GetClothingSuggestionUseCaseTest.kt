package domain.usecase

import com.google.common.truth.Truth.assertThat
import domain.entity.ClothType
import helper.createClothes
import io.mockk.every
import io.mockk.mockk
import org.beijingteam.data.local.cloth.repository.ClothesRepositoryImpl
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
    fun `should return list with correct cloth type when input cloth type `() {
        val clothType = ClothType.HEAVY_CLOTH
        val clothes = listOf(
            createClothes(), createClothes(), createClothes()
        )
        every { clothesRepo.getClothByType(clothType) } returns clothes
        val result = getClothes.getClothByType(clothType)
        assertThat(result).isEqualTo(clothes)
    }

    @OptIn(ExperimentalUuidApi::class)
    @Test
    fun `should return empty list  when input cloth type is not found `() {
        val clothType = ClothType.HEAVY_CLOTH
        val clothes = listOf(
            createClothes(clothType = ClothType.LIGHT_CLOTH),
            createClothes(clothType = ClothType.LIGHT_CLOTH),
            createClothes(clothType = ClothType.LIGHT_CLOTH)
        )
        every { clothesRepo.getClothByType(clothType) } returns clothes
        val result = getClothes.getClothByType(clothType)
        assertThat(result).isEqualTo(clothes)
    }

}