package org.beijingteam.di

import domain.usecase.GetClothingSuggestionUseCase
import domain.usecase.GetWeatherByCityNameUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { GetWeatherByCityNameUseCase(get(), get()) }
    single { GetClothingSuggestionUseCase(get()) }
}