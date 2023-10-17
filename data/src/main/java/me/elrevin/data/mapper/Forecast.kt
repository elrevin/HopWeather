package me.elrevin.data.mapper

import me.elrevin.core.other.dateFormat
import me.elrevin.core.other.extractDate
import me.elrevin.data.local.entity.ForecastEntity
import me.elrevin.data.local.entity.HourForecastEntity
import me.elrevin.data.remote.dto.ForecastDayDto
import me.elrevin.domain.model.Forecast
import me.elrevin.domain.model.HourForecast
import me.elrevin.domain.model.Location

fun ForecastDayDto.toDomainModel() = Forecast(
    lastUpdatedTimestamp = (System.currentTimeMillis() / 1000).toInt(),
    date = this.date!!.dateFormat(),
    dateIso = this.date!!.extractDate(),
    maxTemp = day!!.maxTemp!!.toInt(),
    minTemp = day!!.minTemp!!.toInt(),
    avgTemp = day!!.avgTemp!!.toInt(),
    maxWind = day!!.maxWind!!,
    totalPrecip = day!!.totalPrecip!!,
    avgHumidity = day!!.avgHumidity!!,
    dailyWillItRain = day!!.dailyWillItRain!!,
    dailyChanceOfRain = day!!.dailyChanceOfRain!!,
    dailyWillItSnow = day!!.dailyWillItSnow!!,
    dailyChanceOfSnow = day!!.dailyChanceOfSnow!!,
    conditionText = day!!.condition!!.text!!,
    conditionIcon = "https:" + day!!.condition!!.icon!!,
    conditionCode = day!!.condition!!.code!!,
    uv = day!!.uv!!,
    hours = this.hour.map { it.toDomainModel() },
    sunrise = this.astro!!.sunrise!!,
    sunset = this.astro!!.sunset!!,
)

fun Forecast.toDataEntity(location: Location) = me.elrevin.data.local.entity.Forecast(
    forecast = ForecastEntity(
        id = this.id,
        locationId = location.id,
        lastUpdatedTimestamp = this.lastUpdatedTimestamp,
        date = this.date,
        dateIso = this.dateIso,
        maxTemp = this.maxTemp,
        minTemp = this.minTemp,
        avgTemp = this.avgTemp,
        maxWind = this.maxWind,
        totalPrecip = this.totalPrecip,
        avgHumidity = this.avgHumidity,
        dailyWillItRain = this.dailyWillItRain,
        dailyChanceOfRain = this.dailyChanceOfRain,
        dailyWillItSnow = this.dailyWillItSnow,
        dailyChanceOfSnow = this.dailyChanceOfSnow,
        conditionText = this.conditionText,
        conditionIcon = this.conditionIcon,
        conditionCode = this.conditionCode,
        uv = this.uv,
        sunrise = this.sunrise,
        sunset = this.sunset,
    ),
    hours = this.hours.map { it.toDataEntity(this.id) }
)

fun HourForecast.toDataEntity(forecastId: Int) = HourForecastEntity(
    forecastId = forecastId,
    time = this.time,
    temp = this.temp,
    isDay = this.isDay,
    conditionText = this.conditionText,
    conditionIcon = this.conditionIcon,
    conditionCode = this.conditionCode,
    wind = this.wind,
    windDegree = this.windDegree,
    windDir = this.windDir,
    pressure = this.pressure,
    precip = this.precip,
    humidity = this.humidity,
    cloud = this.cloud,
    feelsLike = this.feelsLike,
    willItRain = this.willItRain,
    chanceOfRain = this.chanceOfRain,
    willItSnow = this.willItSnow,
    chanceOfSnow = this.chanceOfSnow,
    uv = this.uv,
)

fun HourForecastEntity.toDomainModel() = HourForecast(
    time = this.time,
    temp = this.temp,
    isDay = this.isDay,
    conditionText = this.conditionText,
    conditionIcon = this.conditionIcon,
    conditionCode = this.conditionCode,
    wind = this.wind,
    windDegree = this.windDegree,
    windDir = this.windDir,
    pressure = this.pressure,
    precip = this.precip,
    humidity = this.humidity,
    cloud = this.cloud,
    feelsLike = this.feelsLike,
    willItRain = this.willItRain,
    chanceOfRain = this.chanceOfRain,
    willItSnow = this.willItSnow,
    chanceOfSnow = this.chanceOfSnow,
    uv = this.uv,
)

fun me.elrevin.data.local.entity.Forecast.toDomainModel() = Forecast(
    id = this.forecast.id,
    lastUpdatedTimestamp = this.forecast.lastUpdatedTimestamp,
    date = this.forecast.date,
    dateIso = this.forecast.dateIso,
    maxTemp = this.forecast.maxTemp,
    minTemp = this.forecast.minTemp,
    avgTemp = this.forecast.avgTemp,
    maxWind = this.forecast.maxWind,
    totalPrecip = this.forecast.totalPrecip,
    avgHumidity = this.forecast.avgHumidity,
    dailyWillItRain = this.forecast.dailyWillItRain,
    dailyChanceOfRain = this.forecast.dailyChanceOfRain,
    dailyWillItSnow = this.forecast.dailyWillItSnow,
    dailyChanceOfSnow = this.forecast.dailyChanceOfSnow,
    conditionText = this.forecast.conditionText,
    conditionIcon = this.forecast.conditionIcon,
    conditionCode = this.forecast.conditionCode,
    uv = this.forecast.uv,
    hours = this.hours.map { it.toDomainModel() },
    sunrise = this.forecast.sunrise,
    sunset = this.forecast.sunset,
)