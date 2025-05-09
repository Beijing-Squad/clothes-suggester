package domain.repository

import domain.entity.ClothType
import domain.entity.Clothes

interface ClothesRepository {
    fun getClothesByType(clothType: ClothType): List<Clothes>
}