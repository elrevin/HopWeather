package me.elrevin.data.mapper

import me.elrevin.data.local.entity.CurrentWeatherEntity
import me.elrevin.data.local.entity.LocationEntity
import me.elrevin.data.remote.dto.CurrentWeatherDto
import me.elrevin.domain.model.CurrentWeather
import me.elrevin.domain.model.Location
import java.util.Date

fun CurrentWeatherDto.toDomainModel(location: Location) = CurrentWeather(
    location = location,
    temp = current!!.temp!!,
    lastUpdated = current!!.lastUpdated!!.dateFormat(),
    lastUpdatedTimestamp = (System.currentTimeMillis() / 1000).toInt(),
    isDay = current!!.isDay!! == 1,
    conditionText = current!!.condition!!.text!!,
    conditionIcon = current!!.condition!!.icon!!,
    conditionCode = current!!.condition!!.code!!,
    wind = current!!.wind!!,
    windDegree = current!!.windDegree!!,
    windDir = current!!.windDir!!,
    pressure = current!!.pressure!!,
    precip = current!!.precip!!,
    humidity = current!!.humidity!!,
    cloud = current!!.cloud!!,
    feelslike = current!!.feelslike!!,
    gust = current!!.gust!!
)

fun LocationEntity.toDomainModel() = Location(
    id = this.id,
    name = this.name,
    country = this.country,
    region = this.region,
    url = this.url
)

fun me.elrevin.data.local.entity.CurrentWeather.toDomainModel() = CurrentWeather(
    lastUpdated = this.currentWeather.lastUpdated,
    lastUpdatedTimestamp = this.currentWeather.lastUpdatedTimestamp,
    location = this.location.toDomainModel(),
    temp = this.currentWeather.temp,
    isDay = this.currentWeather.isDay,
    conditionText = this.currentWeather.conditionText,
    conditionIcon = this.currentWeather.conditionIcon,
    conditionCode = this.currentWeather.conditionCode,
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
    conditionCode = this.conditionCode,
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