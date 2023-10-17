package me.elrevin.domain.model

import javax.annotation.concurrent.Immutable

@Immutable
data class HourForecast(
    var time: String,
    var temp: Int,
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
    var feelsLike: Int,
    var willItRain: Int,
    var chanceOfRain: Int,
    var willItSnow: Int,
    var chanceOfSnow: Int,
    var uv: Int,
)
