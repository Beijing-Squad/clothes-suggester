package org.beijingteam.presentation

import domain.useCase.GetCoordinateByCityNameUseCase
import domain.useCase.GetWeatherByLongitudeAndLatitudeUseCase

class MainScreen(
    private val weatherByCityName: GetCoordinateByCityNameUseCase,
    private val weatherByLocation: GetWeatherByLongitudeAndLatitudeUseCase
)