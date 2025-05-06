package org.beijingteam.presentation

import org.beijingteam.domain.entity.Clothes
import org.beijingteam.domain.entity.Weather
import org.beijingteam.domain.usecase.GetWeatherByCityNameUseCase
import org.beijingteam.domain.usecase.GetWeatherByLongitudeAndLatitudeUseCase
import org.beijingteam.presentation.consoleIO.ConsoleIO

class MainScreen(
    private val weatherByCityName: GetWeatherByCityNameUseCase,
    private val weatherByLocation: GetWeatherByLongitudeAndLatitudeUseCase,
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

    private fun showClothingSuggestion(weather: Weather) {
        TODO()
    }
}