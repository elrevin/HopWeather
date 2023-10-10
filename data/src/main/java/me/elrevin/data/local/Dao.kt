package me.elrevin.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import me.elrevin.data.local.entity.CurrentWeather
import me.elrevin.data.local.entity.CurrentWeatherEntity
import me.elrevin.data.local.entity.Forecast
import me.elrevin.data.local.entity.ForecastEntity
import me.elrevin.data.local.entity.HourForecastEntity
import me.elrevin.data.local.entity.LocationEntity
import me.elrevin.data.mapper.getIsoDate

@Dao
interface Dao {
    @Transaction
    @Query("SELECT * FROM current_weather WHERE locationId = :locationId")
    fun getCurrentWeather(locationId: String): Flow<CurrentWeather?>

    @Upsert
    suspend fun upsertCurrentWeather(currentWeather: CurrentWeatherEntity)

    @Upsert
    suspend fun upsertLocation(location: LocationEntity)

    @Transaction
    @Query("SELECT * FROM forecast WHERE dateIso >= :date")
    fun getForecast(locationId: String, date: String = getIsoDate()): Flow<List<Forecast>>

    @Transaction
    suspend fun upsertForecast(list: List<Forecast>) {
        _deleteAllForecast()

        list.forEach { forecast ->
            val forecastId = _insertForecast(forecast.forecast)
            forecast.hours.forEach { hour->
                _insertHourForecast(hour.copy(forecastId = forecastId))
            }
        }
    }

    @Query("DELETE FROM forecast")
    suspend fun _deleteAllForecast()

    @Insert
    suspend fun _insertForecast(forecastEntity: ForecastEntity): Int

    @Upsert
    suspend fun _insertHourForecast(hourForecastEntity: HourForecastEntity)
}