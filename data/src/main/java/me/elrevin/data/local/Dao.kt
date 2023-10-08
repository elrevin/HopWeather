package me.elrevin.data.local

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import me.elrevin.data.local.entity.CurrentWeatherEntity

@Dao
interface Dao {
    @Query("SELECT * FROM current_weather")
    fun getCurrentWeather(): Flow<CurrentWeatherEntity>
}