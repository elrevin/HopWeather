package me.elrevin.data.local.entity

import androidx.room.Embedded
import androidx.room.Relation

data class Forecast(
    @Embedded
    val forecast: ForecastEntity,

    @Relation(
        entity = HourForecastEntity::class,
        parentColumn = "id",
        entityColumn = "forecastId"
    )
    val hours: List<HourForecastEntity> = listOf(),
)
