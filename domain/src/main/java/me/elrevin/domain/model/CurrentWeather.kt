package me.elrevin.domain.model

data class CurrentWeather(
    val location: Location,
    val lastUpdated: String,
    val lastUpdatedTimestamp: Int,
    val temp: Double,
    val isDay: Boolean,
    val conditionText: String,
    val conditionIcon: String,
    val conditionCode: Int,
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
