package me.elrevin.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import me.elrevin.data.local.entity.CurrentWeatherEntity
import me.elrevin.data.local.entity.LocationEntity

@Database(
    entities = [
        CurrentWeatherEntity::class,
    ],
    version = 1
)
abstract class DataBase : RoomDatabase() {
    abstract fun getDao(): Dao
}