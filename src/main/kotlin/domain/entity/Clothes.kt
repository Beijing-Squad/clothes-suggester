package domain.entity

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
data class Clothes(
    val id: Uuid = Uuid.random(),
    val clothName: String,
    val clothType: ClothType,
)