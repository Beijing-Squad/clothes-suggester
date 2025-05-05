package org.beijingteam.di

import org.koin.dsl.module

val appModule = listOf(
    dataSourceModule,
    useCaseModule,
    dataSourceModule,
    uiModule
)