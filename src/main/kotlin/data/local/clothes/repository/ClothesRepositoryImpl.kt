package org.beijingteam.data.local.cloth.repository

import org.beijingteam.data.local.cloth.datasource.ClothesDataSource
import domain.entity.Clothes
import domain.entity.ClothType
import domain.repository.ClothesRepository

class ClothesRepositoryImpl(
    private val clothesDataSource: ClothesDataSource
): ClothesRepository {
    override fun getClothByType(clothType: ClothType): List<Clothes> {
        TODO("Not yet implemented")
    }
}