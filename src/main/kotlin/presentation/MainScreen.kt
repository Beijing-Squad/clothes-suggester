package org.beijingteam.presentation

import domain.entity.Cloth
import domain.usecase.GetCoordinateByCityNameUseCase
import domain.usecase.GetWeatherByLongitudeAndLatitudeUseCase
import org.beijingteam.domain.entity.Weather
import org.beijingteam.domain.usecase.GetClothingSuggestionUseCase

import org.beijingteam.presentation.consoleIO.ConsoleIO

class MainScreen(
    private val weatherByCityName: GetCoordinateByCityNameUseCase,
    private val weatherByLocation: GetWeatherByLongitudeAndLatitudeUseCase,
    private val ClothingSuggestion: GetClothingSuggestionUseCase,
    private val consoleIO: ConsoleIO
) {
    fun start() {
        TODO()
    }

    private fun runInputLoop() {
        TODO()
    }

    private fun searchByCityName(input: String): Boolean {
        TODO()
    }

    private fun showWeather(weather: Weather) {
        TODO()
    }

    private fun showClothingSuggestion(clothes: Cloth) {
        TODO()
    }
}