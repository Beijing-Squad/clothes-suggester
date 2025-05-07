package data.cloth.datasource

import domain.entity.Cloth
import domain.entity.ClothType

interface ClothesDataSource {
    fun getClothesByType(clothType: ClothType) : List<Cloth>
}