package org.beijingteam.data.cloth.datasource

import data.cloth.datasource.ClothesDataSource
import domain.entity.Cloth
import domain.entity.ClothType

class ClothesDataSourceImp: ClothesDataSource {
    override fun getClothesByType(clothType: ClothType): List<Cloth> {
        return clothes.filter { it.clothType == clothType }
    }

    @OptIn(kotlin.uuid.ExperimentalUuidApi::class)
    companion object{
        private val clothes = listOf(

            Cloth(clothName = "T-Shirt", clothType = ClothType.LIGHT_CLOTH),
            Cloth(clothName = "Chinos", clothType = ClothType.LIGHT_CLOTH),
            Cloth(clothName = "Polo", clothType = ClothType.LIGHT_CLOTH),
            Cloth(clothName = "Denim Shirt", clothType = ClothType.LIGHT_CLOTH),
            Cloth(clothName = "Cotton Shirt", clothType = ClothType.LIGHT_CLOTH),
            Cloth(clothName = "Sports T-Shirt", clothType = ClothType.LIGHT_CLOTH),
            Cloth(clothName = "Running Shoes", clothType = ClothType.LIGHT_CLOTH),
            Cloth(clothName = "Training Hoodie", clothType = ClothType.LIGHT_CLOTH),

            Cloth(clothName = "Hoodie", clothType = ClothType.MEDIUM_CLOTH),
            Cloth(clothName = "Light Jacket", clothType = ClothType.MEDIUM_CLOTH),
            Cloth(clothName = "Pullover", clothType = ClothType.MEDIUM_CLOTH),
            Cloth(clothName = "Long Sleeve Top", clothType = ClothType.MEDIUM_CLOTH),
            Cloth(clothName = "Jeans", clothType = ClothType.MEDIUM_CLOTH),
            Cloth(clothName = "Cargo Pants", clothType = ClothType.MEDIUM_CLOTH),

            Cloth(clothName = "Thick Jacket", clothType = ClothType.HEAVY_CLOTH),
            Cloth(clothName = "Wool Sweater", clothType = ClothType.HEAVY_CLOTH),
            Cloth(clothName = "Beanie", clothType = ClothType.HEAVY_CLOTH),
            Cloth(clothName = "Padded Vest", clothType = ClothType.HEAVY_CLOTH),
            Cloth(clothName = "Winter Boots", clothType = ClothType.HEAVY_CLOTH),
        )
    }
}