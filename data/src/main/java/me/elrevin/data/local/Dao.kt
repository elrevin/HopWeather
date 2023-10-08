package me.elrevin.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import me.elrevin.data.local.entity.CurrentWeather
import me.elrevin.data.local.entity.CurrentWeatherEntity

@Dao
interface Dao {
    @Query("SELECT * FROM current_weather")
    fun getCurrentWeather(): Flow<List<CurrentWeather>>

    @Transaction
    @Upsert
    suspend fun upsertCurrentWeather(currentWeatherList: List<CurrentWeatherEntity>)
}