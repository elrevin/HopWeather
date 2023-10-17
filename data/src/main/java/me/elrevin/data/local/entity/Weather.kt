package me.elrevin.data.local.entity

import androidx.room.Embedded
import androidx.room.Relation

data class Weather(
    @Embedded
    val location: LocationEntity,

    @Relation(
        entity = CurrentWeatherEntity::class,
        parentColumn = "id",
        entityColumn = "locationId"
    )
    val currentWeather: CurrentWeatherEntity? = null,

    @Relation(
        entity = ForecastEntity::class,
        parentColumn = "id",
        entityColumn = "locationId"
    )
    val forecasts: List<Forecast> = listOf()
)
