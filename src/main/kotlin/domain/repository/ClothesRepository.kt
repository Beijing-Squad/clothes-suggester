package domain.repository

import domain.entity.ClothType
import domain.entity.Clothes
import domain.entity.Clothes
import org.beijingteam.domain.type.ClothType

interface ClothesRepository {
    fun getClothesByType(clothType: ClothType): List<Clothes>
}