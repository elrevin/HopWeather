package me.elrevin.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import me.elrevin.data.local.entity.CurrentWeather
import me.elrevin.data.local.entity.CurrentWeatherEntity
import me.elrevin.data.local.entity.LocationEntity

@Dao
interface Dao {
    @Query("SELECT * FROM current_weather WHERE locationId = :locationId")
    fun getCurrentWeather(locationId: String): Flow<CurrentWeather?>

    @Upsert
    suspend fun upsertCurrentWeather(currentWeather: CurrentWeatherEntity)

    @Upsert
    suspend fun upsertLocation(location: LocationEntity)
}