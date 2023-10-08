package me.elrevin.data.mapper

import me.elrevin.data.local.entity.CurrentWeatherEntity
import me.elrevin.data.local.entity.LocationEntity
import me.elrevin.data.remote.dto.CurrentWeatherDto
import me.elrevin.domain.model.CurrentWeather
import me.elrevin.domain.model.Location

fun CurrentWeatherDto.toWeather(locationId: Int) = CurrentWeatherEntity(
    locationId = locationId,
    temp = current!!.temp!!,
    lastUpdated = current!!.lastUpdated!!.dateFormat(),
    isDay = current!!.isDay!!,
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

fun LocationEntity.toLocation() = Location(
    id = this.id,
    name = this.name,
    country = this.country,
    region = this.region,
)

fun me.elrevin.data.local.entity.CurrentWeather.toWeather() = CurrentWeather(
    lastUpdated = this.currentWeather.lastUpdated,
    location = this.location.toLocation(),
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