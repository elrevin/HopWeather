package me.elrevin.data

import kotlinx.coroutines.flow.Flow
import me.elrevin.data.local.Dao
import me.elrevin.domain.model.CurrentWeather
import me.elrevin.domain.model.Either
import me.elrevin.domain.repository.WeatherRepository

class WeatherRepositoryImpl (
    dao: Dao
): WeatherRepository {
    override fun getCurrentWeather(): Flow<List<CurrentWeather>> {
        TODO("Not yet implemented")
    }

    override suspend fun loadCurrentWeather(): Either<Unit> {
        TODO("Not yet implemented")
    }
}