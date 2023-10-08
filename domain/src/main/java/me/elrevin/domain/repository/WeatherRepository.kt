package me.elrevin.domain.repository

import kotlinx.coroutines.flow.Flow
import me.elrevin.domain.model.CurrentWeather
import me.elrevin.domain.model.Either

interface WeatherRepository {
    fun getCurrentWeather(): Flow<List<CurrentWeather>>
    suspend fun loadCurrentWeather(): Either<Unit>
}