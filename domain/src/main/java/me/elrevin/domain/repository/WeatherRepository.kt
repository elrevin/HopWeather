package me.elrevin.domain.repository

import kotlinx.coroutines.flow.Flow
import me.elrevin.domain.model.CurrentWeather
import me.elrevin.domain.model.Either
import me.elrevin.domain.model.Location

interface WeatherRepository {
    fun getCurrentWeather(): Flow<List<CurrentWeather>>
    suspend fun saveCurrentWeather(currentWeatherList: List<CurrentWeather>)
    suspend fun loadCurrentWeather(location: Location): Either<CurrentWeather>
}