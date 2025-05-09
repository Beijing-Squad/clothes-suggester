package org.beijingteam.service

import domain.entity.ClothType
import domain.entity.Clothes
import domain.usecase.GetClothingSuggestionUseCase
import domain.usecase.GetCoordinateByCityNameUseCase
import domain.usecase.GetWeatherByLongitudeAndLatitudeUseCase
import org.beijingteam.domain.entity.TemperatureCategory
import org.beijingteam.domain.entity.Weather

class ManageWeatherService(
    private val getCoordinateByCityName: GetCoordinateByCityNameUseCase,
    private val getWeatherByCoordinate: GetWeatherByLongitudeAndLatitudeUseCase,
    private val clothingSuggestion: GetClothingSuggestionUseCase,
) {
    suspend fun executeWeatherFlow(cityName: String): Pair<Weather, List<Clothes>> {
        val coordinates = getCoordinateByCityName.getCoordinateByCityName(cityName)
        val weather = getWeatherByCoordinate.getWeatherByCoordinates(coordinates)

        val clothesType = getAppropriateClothType(weather.temperatureCategory)
        val clothes = clothingSuggestion.getClothByType(clothesType)

        return Pair(weather, clothes)
    }

    private fun getAppropriateClothType(tempCategory: TemperatureCategory): ClothType {
        return when (tempCategory) {
            TemperatureCategory.FREEZING, TemperatureCategory.COLD -> ClothType.HEAVY_CLOTH
            TemperatureCategory.COOL, TemperatureCategory.MILD -> ClothType.MEDIUM_CLOTH
            TemperatureCategory.WARM, TemperatureCategory.HOT -> ClothType.LIGHT_CLOTH
        }
    }

}