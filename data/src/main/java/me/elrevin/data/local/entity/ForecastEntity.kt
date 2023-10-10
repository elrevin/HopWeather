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
    val maxTemp: Double,
    val minTemp: Double,
    val avgTemp: Double,
    val maxWind: Double,
    val totalPrecip: Int,
    val avgHumidity: Int,
    val dailyWillItRain: Int,
    val dailyChanceOfRain: Int,
    val dailyWillItSnow: Int,
    val dailyChanceOfSnow: Int,
    val conditionText: String,
    val conditionIcon: String,
    val conditionCode: Int,
    val uv: Int,
    val sunrise: String,
    val sunset: String,
)
