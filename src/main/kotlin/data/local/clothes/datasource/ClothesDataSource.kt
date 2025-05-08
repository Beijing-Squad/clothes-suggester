package org.beijingteam.data.local.cloth.datasource

import domain.entity.Clothes
import domain.entity.ClothType

interface ClothesDataSource {
    fun getClothesByType(clothType: ClothType) : List<Clothes>
}