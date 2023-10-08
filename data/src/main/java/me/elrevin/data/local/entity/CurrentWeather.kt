package me.elrevin.data.local.entity

import androidx.room.Embedded
import androidx.room.Relation

data class CurrentWeather(
    @Embedded
    val currentWeather: CurrentWeatherEntity,

    @Relation(
        entity = LocationEntity::class,
        parentColumn = "locationId",
        entityColumn = "id"
    )
    val location: LocationEntity
)
