package me.elrevin.domain.model

data class Forecast(
    val location: Location,
    val lastUpdatedTimestamp: Int,
    val date: String,
    var maxTemp: Double,
    var minTemp: Double,
    var avgTemp: Double,
    var maxWind: Double,
    var totalPrecip: Int,
    var avgHumidity: Int,
    var dailyWillItRain: Int,
    var dailyChanceOfRain: Int,
    var dailyWillItSnow: Int,
    var dailyChanceOfSnow: Int,
    val conditionText: String,
    val conditionIcon: String,
    val conditionCode: Int,
    val uv: Int,
    val hours: List<HourForecast>,
    val astro: AstroForecast
)
