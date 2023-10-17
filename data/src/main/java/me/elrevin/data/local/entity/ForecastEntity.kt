package me.elrevin.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import me.elrevin.domain.model.HourForecast

@Entity(tableName = "forecast")
data class ForecastEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val locationId: String,
    val lastUpdatedTimestamp: Int,
    val date: String,
    val dateIso: String,
    val maxTemp: Int,
    val minTemp: Int,
    val avgTemp: Int,
    val maxWind: Double,
    val totalPrecip: Double,
    val avgHumidity: Double,
    val dailyWillItRain: Int,
    val dailyChanceOfRain: Int,
    val dailyWillItSnow: Int,
    val dailyChanceOfSnow: Int,
    val conditionText: String,
    val conditionIcon: String,
    val conditionCode: Int,
    val uv: Double,
    val sunrise: String,
    val sunset: String,
)
