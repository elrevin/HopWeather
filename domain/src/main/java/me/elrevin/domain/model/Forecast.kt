package me.elrevin.domain.model

import me.elrevin.core.other.extractHours
import java.time.LocalDateTime
import javax.annotation.concurrent.Immutable

@Immutable
data class Forecast(
    val id: Int = 0,
    val lastUpdatedTimestamp: Int,
    val date: String,
    val dateIso: String,
    var maxTemp: Int,
    var minTemp: Int,
    var avgTemp: Int,
    var maxWind: Double,
    var totalPrecip: Double,
    var avgHumidity: Double,
    var dailyWillItRain: Int,
    var dailyChanceOfRain: Int,
    var dailyWillItSnow: Int,
    var dailyChanceOfSnow: Int,
    val conditionText: String,
    val conditionIcon: String,
    val conditionCode: Int,
    val uv: Double,
    val hours: List<HourForecast>,
    val sunrise: String,
    val sunset: String,
) {
    val maxPressure: Int
        get() = hours.maxOf { it.pressure }

    val onlyActualHours: List<HourForecast>
        get() {
            val hour = LocalDateTime.now().hour

            return hours.filter { hour <= it.time.extractHours() }
        }
}
