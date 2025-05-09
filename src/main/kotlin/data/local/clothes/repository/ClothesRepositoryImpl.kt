package data.local.clothes.repository

import data.local.clothes.datasource.ClothesDataSource
import domain.entity.Clothes
import org.beijingteam.domain.type.ClothType
import domain.repository.ClothesRepository

class ClothesRepositoryImpl(
    private val clothesDataSource: ClothesDataSource
) : ClothesRepository {
    override fun getClothesByType(clothType: ClothType): List<Clothes> {
        return clothesDataSource.getClothesByType(clothType)
    }
}