package helper

import org.beijingteam.domain.entity.Clothes

fun createClothes(
    upperBody: String = "T-Shirt",
    lowerBody: String = "Jeans",
    footWear: String = "Sneakers"
): Clothes {
    return Clothes(
        upperBody = upperBody,
        lowerBody = lowerBody,
        footWear = footWear
    )
}