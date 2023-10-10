package me.elrevin.data.mapper

import me.elrevin.data.remote.dto.ForecastDayDto
import me.elrevin.data.remote.dto.ForecastDto
import me.elrevin.domain.model.Forecast
import me.elrevin.domain.model.Location

fun ForecastDto.toDomainModel(location: Location): List<Forecast> =
    this.forecast!!.forecastday.map { it.toDomainModel(location) }

fun ForecastDayDto.toDomainModel(location: Location) = Forecast(
    location = location,
    lastUpdatedTimestamp = (System.currentTimeMillis() / 1000).toInt(),
    date = this.date!!.dateFormat(),
    maxTemp = day!!.maxTemp!!,
    minTemp = day!!.minTemp!!,
    avgTemp = day!!.avgTemp!!,
    maxWind = day!!.maxWind!!,
    totalPrecip = day!!.totalPrecip!!,
    avgHumidity = day!!.avgHumidity!!,
    dailyWillItRain = day!!.dailyWillItRain!!,
    dailyChanceOfRain = day!!.dailyChanceOfRain!!,
    dailyWillItSnow = day!!.dailyWillItSnow!!,
    dailyChanceOfSnow = day!!.dailyChanceOfSnow!!,
    conditionText = day!!.condition!!.text!!,
    conditionIcon = day!!.condition!!.icon!!,
    conditionCode = day!!.condition!!.code!!,
    uv = day!!.uv!!,
    hours = this.hour.map { it.toDomainModel() },
    astro = this.astro!!.toDomainModel()
)