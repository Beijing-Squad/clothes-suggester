package org.beijingteam.di

import data.local.clothes.datasource.ClothesDataSource
import data.local.clothes.datasource.ClothesDataSourceImpl
import data.remote.location.datasource.LocationRemoteDataSource
import data.remote.location.datasource.LocationRemoteDataSourceImpl
import data.remote.location.repository.LocationRepositoryImpl
import data.remote.location.repository.mapper.CityLocationMapper
import domain.repository.ClothesRepository
import domain.repository.LocationRepository
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import kotlinx.serialization.json.Json
import org.beijingteam.data.local.cloth.repository.ClothesRepositoryImpl
import org.beijingteam.data.remote.weather.datasource.WeatherRemoteDataSource
import org.beijingteam.data.remote.weather.datasource.WeatherRemoteDataSourceImpl
import org.beijingteam.data.remote.weather.repository.WeatherRepositoryImpl
import org.beijingteam.data.remote.weather.repository.mapper.WeatherMapper
import org.beijingteam.domain.repository.WeatherRepository
import org.koin.dsl.module

val dataSourceModule = module {

    single<ClothesDataSource> { ClothesDataSourceImpl() }
    single<ClothesRepository> { ClothesRepositoryImpl(get()) }
    single<WeatherRepository> { WeatherRepositoryImpl(get(), get()) }
    single<LocationRepository> { LocationRepositoryImpl(get(), get()) }

    single<HttpClient> { HttpClient(CIO) }
    single { Json { ignoreUnknownKeys = true } }

    single<LocationRemoteDataSource> { LocationRemoteDataSourceImpl(get(), get()) }
    single<WeatherRemoteDataSource> { WeatherRemoteDataSourceImpl(get(), get()) }

    single { CityLocationMapper() }
    single { WeatherMapper() }

}