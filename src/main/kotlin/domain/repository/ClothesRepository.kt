package domain.repository

import domain.entity.Clothes
import domain.entity.ClothType

interface ClothesRepository {
    fun getClothByType(clothType: ClothType): List<Clothes>
}