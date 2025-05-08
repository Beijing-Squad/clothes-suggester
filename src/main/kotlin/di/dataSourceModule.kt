package org.beijingteam.di

import data.cloth.datasource.ClothesDataSource
import data.weather.datasource.LocationRemoteDataSource
import data.weather.repository.LocationRepositoryImpl
import domain.repository.ClothesRepository
import domain.repository.LocationRepository
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import kotlinx.serialization.json.Json
import org.beijingteam.data.cloth.datasource.ClothesDataSourceImp
import org.beijingteam.data.cloth.repository.ClothesRepositoryImpl
import org.beijingteam.data.weather.datasource.LocationRemoteDataSourceImpl
import org.beijingteam.data.weather.datasource.WeatherRemoteDataSource
import org.beijingteam.data.weather.datasource.WeatherRemoteDataSourceImpl
import org.beijingteam.data.weather.repository.WeatherRepositoryImpl
import org.beijingteam.data.weather.repository.mapper.CityLocationMapper
import org.beijingteam.data.weather.repository.mapper.WeatherMapper
import org.beijingteam.domain.repository.WeatherRepository
import org.koin.dsl.module

val dataSourceModule = module {

    single<ClothesDataSource> { ClothesDataSourceImp() }
    single<ClothesRepository> { ClothesRepositoryImpl(get()) }
    single<WeatherRepository> { WeatherRepositoryImpl(get(), get()) }
    single<LocationRepository> { LocationRepositoryImpl(get(), get()) }

    single<HttpClient> { HttpClient(CIO) }
    single { Json{ignoreUnknownKeys = true} }

    single<LocationRemoteDataSource> { LocationRemoteDataSourceImpl(get(), get()) }
    single<WeatherRemoteDataSource> { WeatherRemoteDataSourceImpl(get(), get()) }

    single { CityLocationMapper() }
    single { WeatherMapper() }

}