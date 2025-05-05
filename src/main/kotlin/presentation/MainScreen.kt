package org.beijingteam.presentation

import org.beijingteam.domain.usecase.GetWeatherByCityNameUseCase
import org.beijingteam.domain.usecase.GetWeatherByLongitudeAndLatitudeUseCase

class MainScreen(
    private val weatherByCityName: GetWeatherByCityNameUseCase,
    private val weatherByLocation: GetWeatherByLongitudeAndLatitudeUseCase
)