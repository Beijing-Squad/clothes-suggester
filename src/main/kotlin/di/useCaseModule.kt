package org.beijingteam.di

import domain.usecase.GetClothingSuggestionUseCase
import domain.usecase.GetCoordinateByCityNameUseCase
import domain.usecase.GetWeatherByLongitudeAndLatitudeUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { GetCoordinateByCityNameUseCase(get()) }
    single { GetWeatherByLongitudeAndLatitudeUseCase(get()) }
    single { GetClothingSuggestionUseCase(get()) }
}