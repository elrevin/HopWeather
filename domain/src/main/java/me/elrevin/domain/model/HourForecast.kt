package me.elrevin.domain.model

data class HourForecast(
    var time: String,
    var temp: Double,
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
    var uv: Int,

)
