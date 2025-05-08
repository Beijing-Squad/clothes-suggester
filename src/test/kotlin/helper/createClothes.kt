package helper

import domain.entity.Clothes
import domain.entity.ClothType
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
fun createClothes(
    id: Uuid  = Uuid.random(),
    clothName: String = "T-Shirt",
    clothType: ClothType = ClothType.HEAVY_CLOTH
): Clothes {
    return Clothes(
        id =id ,
        clothName = clothName,
        clothType = clothType
    )
}