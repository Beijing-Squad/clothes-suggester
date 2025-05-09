package data.local.clothes.datasource

import domain.entity.Clothes
import org.beijingteam.domain.type.ClothType

interface ClothesDataSource {
    fun getClothesByType(clothType: ClothType) : List<Clothes>
}