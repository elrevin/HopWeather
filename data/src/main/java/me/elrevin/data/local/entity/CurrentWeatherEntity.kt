package me.elrevin.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "current_weather")
data class CurrentWeatherEntity(
    @PrimaryKey
    val locationId: String,
    val lastUpdated: String,
    val lastUpdatedTimestamp: Int,
    val temp: Double,
    val isDay: Boolean,
    val conditionText: String,
    val conditionIcon: String,
    val conditionIllustration: String,
    val wind: Double,
    val windDegree: Int,
    val windDir: String,
    val pressure: Int,
    val precip: Int,
    val humidity: Int,
    val cloud: Int,
    val feelslike: Double,
    val gust: Double
)
