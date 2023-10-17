package me.elrevin.domain.repository

import kotlinx.coroutines.flow.Flow
import me.elrevin.domain.model.Either
import me.elrevin.domain.model.Location
import me.elrevin.domain.model.Weather

interface WeatherRepository {
    fun getWeather(location: Location): Flow<Weather?>
    suspend fun saveWeather(weather: Weather)
    suspend fun loadWeather(location: Location): Either<Weather>
}