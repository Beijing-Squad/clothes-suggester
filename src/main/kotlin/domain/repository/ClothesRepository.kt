package domain.repository

import domain.entity.Cloth
import domain.entity.ClothType

interface ClothesRepository {
    fun getClothByType(clothType: ClothType): List<Cloth>
}