package me.elrevin.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import me.elrevin.data.local.entity.CurrentWeatherEntity
import me.elrevin.data.local.entity.ForecastEntity
import me.elrevin.data.local.entity.HourForecastEntity
import me.elrevin.data.local.entity.LocationEntity

@Database(
    entities = [
        CurrentWeatherEntity::class,
        LocationEntity::class,
        ForecastEntity::class,
        HourForecastEntity::class
    ],
    version = 5
)
abstract class DataBase : RoomDatabase() {
    abstract fun getDao(): Dao
}