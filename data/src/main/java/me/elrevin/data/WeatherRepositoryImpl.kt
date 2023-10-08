package me.elrevin.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import me.elrevin.data.local.Dao
import me.elrevin.data.mapper.toDataEntity
import me.elrevin.data.mapper.toDomainModel
import me.elrevin.data.remote.WeatherRemoteSource
import me.elrevin.domain.model.CurrentWeather
import me.elrevin.domain.model.Either
import me.elrevin.domain.model.Location
import me.elrevin.domain.repository.WeatherRepository

class WeatherRepositoryImpl (
    private val dao: Dao,
    private val remoteSource: WeatherRemoteSource
): WeatherRepository {
    override fun getCurrentWeather(): Flow<List<CurrentWeather>> = dao.getCurrentWeather()
        .map { it.map { item -> item.toDomainModel() } }

    override suspend fun saveCurrentWeather(currentWeatherList: List<CurrentWeather>) {
        dao.upsertCurrentWeather(currentWeatherList.map { it.toDataEntity() })
    }

    override suspend fun loadCurrentWeather(location: Location): Either<CurrentWeather> {
        val result = remoteSource.loadCurrentWeather(location.url)
        if (result.isSuccess()) {
            return Either.success(result.getValue().toDomainModel(location))
        }
        return Either.fromEither(result)
    }
}