package me.elrevin.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import me.elrevin.data.local.entity.CurrentWeatherEntity
import me.elrevin.data.local.entity.ForecastEntity
import me.elrevin.data.local.entity.HourForecastEntity
import me.elrevin.data.local.entity.LocationEntity
import me.elrevin.data.local.entity.Weather

@Dao
interface Dao {
    @Transaction
    @Query("SELECT * FROM location WHERE id = :locationId")
    fun getWeather(locationId: String): Flow<Weather?>

    @Transaction
    suspend fun upsertWeather(weather: Weather) {
        if (weather.currentWeather != null) {
            upsertCurrentWeather(weather.currentWeather)
        }
        _deleteForecasts(weather.location.id)
        weather.forecasts.forEach {forecast ->
            val forecastId = _insertForecast(forecast.forecast).toInt()
            forecast.hours.forEach { hour->
                _insertHourForecast(hour.copy(forecastId = forecastId))
            }
        }
    }

    @Upsert
    suspend fun upsertCurrentWeather(currentWeather: CurrentWeatherEntity)

    @Upsert
    suspend fun upsertLocation(location: LocationEntity)


    @Query("DELETE FROM forecast WHERE locationId = :locationId")
    suspend fun _deleteForecasts(locationId: String)

    @Insert
    suspend fun _insertForecast(forecastEntity: ForecastEntity): Long

    @Upsert
    suspend fun _insertHourForecast(hourForecastEntity: HourForecastEntity)

    @Query("SELECT * FROM location")
    fun getLocations(): Flow<List<LocationEntity>>

    @Query("SELECT * FROM location WHERE '' = :name OR name LIKE '%' || :name || '%'")
    suspend fun getLocationsByName(name: String): List<LocationEntity>

    @Delete
    suspend fun deleteLocation(location: LocationEntity)
}