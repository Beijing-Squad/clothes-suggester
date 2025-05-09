package domain.usecase

import org.beijingteam.domain.type.ClothType
import domain.entity.Clothes
import domain.repository.ClothesRepository
import org.beijingteam.domain.entity.TemperatureCategory
import org.beijingteam.domain.entity.Weather

class GetClothingSuggestionUseCase(
    private val clothesRepository: ClothesRepository
) {

    fun getClothesByType(weather: Weather): List<Clothes> {
        return clothesRepository.getClothesByType(
            getAppropriateClothesType(weather.temperatureCategory)
        )
    }

    private fun getAppropriateClothesType(tempCategory: TemperatureCategory): ClothType {
        return when (tempCategory) {
            TemperatureCategory.FREEZING, TemperatureCategory.COLD -> ClothType.HEAVY_CLOTH
            TemperatureCategory.COOL, TemperatureCategory.MILD -> ClothType.MEDIUM_CLOTH
            TemperatureCategory.WARM, TemperatureCategory.HOT -> ClothType.LIGHT_CLOTH
        }
    }

}