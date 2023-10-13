package me.elrevin.data.mapper

import me.elrevin.data.local.entity.CurrentWeatherEntity
import me.elrevin.data.remote.dto.WeatherDto
import me.elrevin.domain.model.CurrentWeather
import me.elrevin.domain.model.Location

fun me.elrevin.data.local.entity.CurrentWeather.toDomainModel() = CurrentWeather(
    lastUpdated = this.currentWeather.lastUpdated,
    lastUpdatedTimestamp = this.currentWeather.lastUpdatedTimestamp,
    location = this.location.toDomainModel(),
    temp = this.currentWeather.temp,
    isDay = this.currentWeather.isDay,
    conditionText = this.currentWeather.conditionText,
    conditionIcon = this.currentWeather.conditionIcon,
    conditionIllustration = this.currentWeather.conditionIllustration,
    wind = this.currentWeather.wind,
    windDegree = this.currentWeather.windDegree,
    windDir = this.currentWeather.windDir,
    pressure = this.currentWeather.pressure,
    precip = this.currentWeather.precip,
    humidity = this.currentWeather.humidity,
    cloud = this.currentWeather.cloud,
    feelslike = this.currentWeather.feelslike,
    gust = this.currentWeather.gust,
)

fun CurrentWeather.toDataEntity() = CurrentWeatherEntity(
    locationId = this.location.id,
    lastUpdated = this.lastUpdated,
    lastUpdatedTimestamp = this.lastUpdatedTimestamp,
    temp = this.temp,
    isDay = this.isDay,
    conditionText = this.conditionText,
    conditionIcon = this.conditionIcon,
    conditionIllustration = this.conditionIllustration,
    wind = this.wind,
    windDegree = this.windDegree,
    windDir = this.windDir,
    pressure = this.pressure,
    precip = this.precip,
    humidity = this.humidity,
    cloud = this.cloud,
    feelslike = this.feelslike,
    gust = this.gust,
)