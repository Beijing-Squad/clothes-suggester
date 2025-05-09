package domain.repository

import domain.entity.Clothes
import org.beijingteam.domain.type.ClothType

interface ClothesRepository {
    fun getClothByType(clothType: ClothType): List<Clothes>
}