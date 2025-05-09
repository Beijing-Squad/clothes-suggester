package data.local.clothes.datasource

import domain.entity.Clothes
import org.beijingteam.domain.type.ClothType
import domain.entity.ClothType
import domain.entity.Clothes

class ClothesDataSourceImpl : ClothesDataSource {
    override fun getClothesByType(clothType: ClothType): List<Clothes> {
        return filteredClothesByType[clothType] ?: emptyList()
    }

    @OptIn(kotlin.uuid.ExperimentalUuidApi::class)
    companion object {
        private val clothes = listOf(

            Clothes(clothName = "T-Shirt", clothType = ClothType.LIGHT_CLOTH),
            Clothes(clothName = "Chinos", clothType = ClothType.LIGHT_CLOTH),
            Clothes(clothName = "Polo", clothType = ClothType.LIGHT_CLOTH),
            Clothes(clothName = "Denim Shirt", clothType = ClothType.LIGHT_CLOTH),
            Clothes(clothName = "Cotton Shirt", clothType = ClothType.LIGHT_CLOTH),
            Clothes(clothName = "Sports T-Shirt", clothType = ClothType.LIGHT_CLOTH),
            Clothes(clothName = "Running Shoes", clothType = ClothType.LIGHT_CLOTH),
            Clothes(clothName = "Training Hoodie", clothType = ClothType.LIGHT_CLOTH),

            Clothes(clothName = "Hoodie", clothType = ClothType.MEDIUM_CLOTH),
            Clothes(clothName = "Light Jacket", clothType = ClothType.MEDIUM_CLOTH),
            Clothes(clothName = "Pullover", clothType = ClothType.MEDIUM_CLOTH),
            Clothes(clothName = "Long Sleeve Top", clothType = ClothType.MEDIUM_CLOTH),
            Clothes(clothName = "Jeans", clothType = ClothType.MEDIUM_CLOTH),
            Clothes(clothName = "Cargo Pants", clothType = ClothType.MEDIUM_CLOTH),

            Clothes(clothName = "Thick Jacket", clothType = ClothType.HEAVY_CLOTH),
            Clothes(clothName = "Wool Sweater", clothType = ClothType.HEAVY_CLOTH),
            Clothes(clothName = "Beanie", clothType = ClothType.HEAVY_CLOTH),
            Clothes(clothName = "Padded Vest", clothType = ClothType.HEAVY_CLOTH),
            Clothes(clothName = "Winter Boots", clothType = ClothType.HEAVY_CLOTH),
        )

        private val filteredClothesByType: Map<ClothType, List<Clothes>> = clothes.groupBy { it.clothType }
    }
}