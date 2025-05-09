package org.beijingteam.presentation

import domain.entity.Clothes
import domain.exception.MissingLocationException
import domain.exception.MissingTemperatureException
import domain.exception.MissingWeatherConditionException
import kotlinx.coroutines.runBlocking
import org.beijingteam.domain.entity.Weather
import org.beijingteam.presentation.consoleIO.ConsoleIO
import org.beijingteam.service.ManageWeatherService

class MainScreen(
    private val weatherService: ManageWeatherService,
    private val consoleIO: ConsoleIO
) {
    fun start() {
        consoleIO.showWithLine("╔═════════════════════════════════════════════╗")
        consoleIO.showWithLine("║         👋 Welcome to Clothes Suggester     ║")
        consoleIO.showWithLine("╠═════════════════════════════════════════════╣")
        consoleIO.showWithLine("║ Get weather & clothing tips by city name    ║")
        consoleIO.showWithLine("║ Type a city name or 'exit' to quit          ║")
        consoleIO.showWithLine("╚═════════════════════════════════════════════╝\n")
        runInputLoop()
    }

    private fun runInputLoop() {
        while (true) {
            consoleIO.show("\n🌍 Enter city name : ")
            val input = consoleIO.read().orEmpty()
            if (input.isEmpty()) continue
            if (input.lowercase() == "exit") break

            runBlocking {
                try {
                    consoleIO.showWithLine("🔄 Fetching weather data for $input...")
                    val (weather, clothes) = weatherService.executeWeatherFlow(input)
                    showWeather(weather)
                    showClothingSuggestion(clothes)
                } catch (locationError: MissingLocationException) {
                    consoleIO.showWithLine("❌ Error occurred: ${locationError.message}")
                } catch (weatherError: MissingWeatherConditionException) {
                    consoleIO.showWithLine("❌ Error occurred: ${weatherError.message}")
                } catch (tempError: MissingTemperatureException) {
                    consoleIO.showWithLine("❌ Error occurred: ${tempError.message}")
                } catch (e: Exception) {
                    consoleIO.showWithLine("❌ Error occurred: ${e.message}")
                }
            }
        }
    }

    private fun showWeather(weather: Weather) {
        consoleIO.showWithLine("\n🌦️ Weather Info:  ")
        consoleIO.showWithLine("===========================================")
        consoleIO.showWithLine("Temperature           | ${weather.temperature}°C")
        consoleIO.showWithLine("Weather Condition     | ${weather.weatherCondition}")
        consoleIO.showWithLine("Temperature Category  | ${weather.temperatureCategory}")
        consoleIO.showWithLine("===========================================")
    }

    private fun showClothingSuggestion(clothes: List<Clothes>) {
        consoleIO.showWithLine("\n🧣 Clothing Suggestions:")
        consoleIO.showWithLine("─────────────────────────────────────")
        clothes.forEach {
            consoleIO.showWithLine("🔹 • ${it.clothType}: ${it.clothName}")
        }
        consoleIO.showWithLine("─────────────────────────────────────\n")
    }

}