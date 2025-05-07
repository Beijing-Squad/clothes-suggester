package org.beijingteam.presentation

import domain.useCase.GetWeatherByCityNameUseCase
import domain.useCase.GetWeatherByLongitudeAndLatitudeUseCase

class MainScreen(
    private val weatherByCityName: GetWeatherByCityNameUseCase,
    private val weatherByLocation: GetWeatherByLongitudeAndLatitudeUseCase
)