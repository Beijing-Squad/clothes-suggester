package org.beijingteam

import org.beijingteam.di.appModule
import org.beijingteam.presentation.MainScreen
import org.koin.core.context.startKoin
import org.koin.mp.KoinPlatform.getKoin

fun main() {
    startKoin {
        modules(appModule)
    }

    val program: MainScreen = getKoin().get()
    program.start()
}