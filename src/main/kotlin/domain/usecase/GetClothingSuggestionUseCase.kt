package domain.usecase

import org.beijingteam.domain.type.ClothType
import domain.entity.Clothes
import domain.repository.ClothesRepository

class GetClothingSuggestionUseCase(private val clothesRepository: ClothesRepository) {

    fun getClothByType(clothType: ClothType): List<Clothes> {
        return clothesRepository.getClothByType(clothType)
    }

}