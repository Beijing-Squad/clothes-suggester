package org.beijingteam.presentation

import domain.entity.ClothType
import domain.exception.MissingLocationException
import domain.exception.MissingTemperatureException
import domain.exception.MissingWeatherCodeException
import domain.usecase.GetCoordinateByCityNameUseCase
import domain.usecase.GetWeatherByLongitudeAndLatitudeUseCase
import kotlinx.coroutines.runBlocking
import org.beijingteam.domain.entity.TemperatureCategory
import org.beijingteam.domain.entity.Weather
import org.beijingteam.domain.useCase.GetClothingSuggestionUseCase
import org.beijingteam.presentation.consoleIO.ConsoleIO

class MainScreen(
    private val getWeatherByCoordinate: GetWeatherByLongitudeAndLatitudeUseCase,
    private val getCoordinateByCityName: GetCoordinateByCityNameUseCase,
    private val clothingSuggestion: GetClothingSuggestionUseCase,
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
                    val coordinate = getCoordinateByCityName.getCoordinateByCityName(input)
                    val weather = getWeatherByCoordinate.getWeatherByCoordinates(coordinate)
                    showWeather(weather)
                    showClothingSuggestion(weather.temperatureCategory)

                } catch (locationError: MissingLocationException) {
                    consoleIO.showWithLine("❌ Error occurred: ${locationError.message}")
                } catch (weatherError: MissingWeatherCodeException) {
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

    private fun showClothingSuggestion(tempCategory: TemperatureCategory) {
        val clothes = clothingSuggestion.getClothByType(
            getAppropriateClothType(tempCategory)
        )
        consoleIO.showWithLine("\n🧣 Clothing Suggestions:")
        consoleIO.showWithLine("─────────────────────────────────────")
        clothes.forEach {
            consoleIO.showWithLine("🔹 • ${it.clothType}: ${it.clothName}")
        }
        consoleIO.showWithLine("─────────────────────────────────────\n")
    }

    private fun getAppropriateClothType(tempCategory: TemperatureCategory): ClothType {
        return when (tempCategory) {
            TemperatureCategory.FREEZING, TemperatureCategory.COLD -> ClothType.HEAVY_CLOTH
            TemperatureCategory.COOL, TemperatureCategory.MILD -> ClothType.MEDIUM_CLOTH
            TemperatureCategory.WARM, TemperatureCategory.HOT -> ClothType.LIGHT_CLOTH
        }
    }
}