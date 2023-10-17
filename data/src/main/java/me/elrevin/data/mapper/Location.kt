package me.elrevin.data.mapper

import me.elrevin.data.local.entity.LocationEntity
import me.elrevin.data.remote.dto.LocationDto
import me.elrevin.domain.model.Location

fun LocationDto.toDomainModel() = Location(
    id = this.url ?: "",
    name = this.name!!,
    country = this.country!!,
    region = this.region!!
)

fun LocationEntity.toDomainModel() = Location(
    id = this.id,
    name = this.name,
    country = this.country,
    region = this.region,
)

fun Location.toDataEntity() = LocationEntity(
    id = this.id,
    name = this.name,
    country = this.country,
    region = this.region,
)