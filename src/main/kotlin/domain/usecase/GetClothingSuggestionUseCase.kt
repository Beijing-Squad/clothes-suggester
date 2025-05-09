package domain.usecase

import domain.entity.Clothes
import domain.repository.ClothesRepository
import org.beijingteam.domain.type.ClothType
import org.beijingteam.domain.type.TemperatureCategory

class GetClothingSuggestionUseCase(
    private val clothesRepository: ClothesRepository
) {

    fun getClothByTempCategory(tempCategory: TemperatureCategory): List<Clothes> {
        return clothesRepository.getClothByType(
            getAppropriateClothType(tempCategory)
        )
    }

    private fun getAppropriateClothType(tempCategory: TemperatureCategory): ClothType {
        return when (tempCategory) {
            TemperatureCategory.FREEZING, TemperatureCategory.COLD -> ClothType.HEAVY_CLOTH
            TemperatureCategory.COOL, TemperatureCategory.MILD -> ClothType.MEDIUM_CLOTH
            TemperatureCategory.WARM, TemperatureCategory.HOT -> ClothType.LIGHT_CLOTH
        }
    }

}