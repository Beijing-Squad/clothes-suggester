package org.beijingteam.domain.useCase

import domain.entity.Cloth
import domain.entity.ClothType
import domain.repository.ClothesRepository

class GetClothingSuggestionUseCase(private val clothesRepository: ClothesRepository) {

    fun getClothByType(clothType: ClothType): List<Cloth> {
        return clothesRepository.getClothByType(clothType)
    }

}