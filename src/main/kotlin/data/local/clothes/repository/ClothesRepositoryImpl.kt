package org.beijingteam.data.local.cloth.repository

import data.local.clothes.datasource.ClothesDataSource
import domain.entity.Clothes
import domain.entity.ClothType
import domain.repository.ClothesRepository

class ClothesRepositoryImpl(
    private val clothesDataSource: ClothesDataSource
) : ClothesRepository {
    override fun getClothesByType(clothType: ClothType): List<Clothes> {
        return clothesDataSource.getClothesByType(clothType)
    }
}