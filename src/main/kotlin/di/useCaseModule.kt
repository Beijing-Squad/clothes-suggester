package org.beijingteam.di

import domain.usecase.GetCoordinateByCityNameUseCase
import domain.usecase.GetWeatherByLongitudeAndLatitudeUseCase
import org.beijingteam.domain.useCase.GetClothingSuggestionUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { GetCoordinateByCityNameUseCase(get()) }
    single { GetWeatherByLongitudeAndLatitudeUseCase(get()) }
    single { GetClothingSuggestionUseCase(get()) }
}