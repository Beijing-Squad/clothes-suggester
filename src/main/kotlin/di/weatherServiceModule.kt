package org.beijingteam.di

import org.beijingteam.service.ManageWeatherService
import org.koin.dsl.module

val weatherServiceModule = module {
    single { ManageWeatherService(get(), get(), get()) }
}