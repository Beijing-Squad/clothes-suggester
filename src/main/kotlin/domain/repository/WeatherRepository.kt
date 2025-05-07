package org.beijingteam.domain.repository

import domain.entity.LocationCoordinate
import org.beijingteam.domain.entity.Weather

interface WeatherRepository {
    suspend fun getWeatherByCoordinate(locationCoordinate: LocationCoordinate): Weather
}