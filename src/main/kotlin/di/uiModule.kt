package org.beijingteam.di


import org.beijingteam.presentation.MainScreen
import org.beijingteam.presentation.consoleIO.ConsoleIO
import org.beijingteam.presentation.consoleIO.ConsoleIOImpl
import org.koin.dsl.module

val uiModule = module {
    single<ConsoleIO> { ConsoleIOImpl() }
    single {
        MainScreen(
            get(), get(),
            get(), get()
        )
    }
}