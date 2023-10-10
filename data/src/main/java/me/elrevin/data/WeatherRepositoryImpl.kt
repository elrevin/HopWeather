package me.elrevin.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import me.elrevin.data.local.Dao
import me.elrevin.data.mapper.toDataEntity
import me.elrevin.data.mapper.toDomainModel
import me.elrevin.data.mapper.toCurrentWeatherDomainModel
import me.elrevin.data.remote.WeatherRemoteSource
import me.elrevin.domain.model.CurrentWeather
import me.elrevin.domain.model.Either
import me.elrevin.domain.model.Forecast
import me.elrevin.domain.model.Location
import me.elrevin.domain.repository.WeatherRepository

class WeatherRepositoryImpl (
    private val dao: Dao,
    private val remoteSource: WeatherRemoteSource
): WeatherRepository {
    override fun getCurrentWeather(location: Location): Flow<CurrentWeather?> =
        dao.getCurrentWeather(location.id).map {
            it?.toDomainModel()
        }

    override suspend fun saveCurrentWeather(currentWeather: CurrentWeather) {
        dao.upsertCurrentWeather(currentWeather.toDataEntity() )
    }

    override suspend fun loadCurrentWeather(location: Location): Either<CurrentWeather> {
        val result = remoteSource.loadCurrentWeather(location.id)
        if (result.isSuccess()) {
            return Either.success(result.getValue().toCurrentWeatherDomainModel(location))
        }
        return Either.fromEither(result)
    }

    override fun getForecast(location: Location): Flow<List<Forecast>> =
        dao.getForecast(location.id).map { list ->
            list.map { item ->
                item.toDomainModel()
            }
        }

    override suspend fun loadForecast(location: Location): Either<List<Forecast>> {
        val result = remoteSource.loadForecast(location.id)
        if (result.isSuccess()) {
            return Either.success(result.getValue().toDomainModel(location))
        }
        return Either.fromEither(result)
    }

    override suspend fun saveForecast(list: List<Forecast>) {
        dao.upsertForecast(list.map { it.toDataEntity() })
    }


}