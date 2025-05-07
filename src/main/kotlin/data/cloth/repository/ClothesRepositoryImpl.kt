package org.beijingteam.data.cloth.repository

import data.cloth.datasource.ClothesDataSource
import domain.entity.Cloth
import domain.entity.ClothType
import domain.repository.ClothesRepository

class ClothesRepositoryImpl(
    private val clothesDataSource: ClothesDataSource
): ClothesRepository {
    override fun getClothByType(clothType: ClothType): List<Cloth> {
        TODO("Not yet implemented")
    }
}