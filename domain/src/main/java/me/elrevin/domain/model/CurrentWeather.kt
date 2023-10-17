package me.elrevin.domain.model

data class CurrentWeather(
    val lastUpdated: String,
    val lastUpdatedTimestamp: Int,
    val temp: Int,
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
    val feelslike: Int,
    val gust: Double
)
