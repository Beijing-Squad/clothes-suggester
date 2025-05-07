package helper

import domain.entity.Cloth
import domain.entity.ClothType
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
fun createClothes(
    id: Uuid  = Uuid.random(),
    clothName: String = "T-Shirt",
    clothType: ClothType = ClothType.HEAVY_CLOTH
): Cloth {
    return Cloth(
        id =id ,
        clothName = clothName,
        clothType = clothType
    )
}