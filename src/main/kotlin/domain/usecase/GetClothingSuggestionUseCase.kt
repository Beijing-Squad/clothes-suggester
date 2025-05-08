package org.beijingteam.domain.useCase

import domain.entity.ClothType
import domain.entity.Clothes
import domain.repository.ClothesRepository

class GetClothingSuggestionUseCase(private val clothesRepository: ClothesRepository) {

    fun getClothByType(clothType: ClothType): List<Clothes> {
        return clothesRepository.getClothByType(clothType)
    }

}