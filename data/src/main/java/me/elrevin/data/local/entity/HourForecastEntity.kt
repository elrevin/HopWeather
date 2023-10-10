package me.elrevin.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "hour_forecast",
    primaryKeys = ["forecastId", "time"],
    foreignKeys = [
        ForeignKey(
            ForecastEntity::class,
            parentColumns = ["id"],
            childColumns = ["forecastId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class HourForecastEntity(
    val forecastId: Int,
    var temp: Double,
    val time: String,
    var isDay: Boolean,
    val conditionText: String,
    val conditionIcon: String,
    val conditionCode: Int,
    var wind: Double,
    var windDegree: Int,
    var windDir: String,
    var pressure: Int,
    var precip: Int,
    var humidity: Int,
    var cloud: Int,
    var feelsLike: Double,
    var willItRain: Int,
    var chanceOfRain: Int,
    var willItSnow: Int,
    var chanceOfSnow: Int,
    var uv: Int
)
