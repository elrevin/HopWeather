package me.elrevin.domain.repository

import kotlinx.coroutines.flow.Flow
import me.elrevin.domain.model.CurrentWeather
import me.elrevin.domain.model.Either
import me.elrevin.domain.model.Forecast
import me.elrevin.domain.model.Location

interface WeatherRepository {
    fun getCurrentWeather(location: Location): Flow<CurrentWeather?>
    suspend fun saveCurrentWeather(currentWeather: CurrentWeather)
    suspend fun loadCurrentWeather(location: Location): Either<CurrentWeather>

    fun getForecast(location: Location): Flow<List<Forecast>>
    suspend fun loadForecast(location: Location): Either<List<Forecast>>
    suspend fun saveForecast(list: List<Forecast>)
}