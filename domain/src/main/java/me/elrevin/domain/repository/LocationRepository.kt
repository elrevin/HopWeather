package me.elrevin.domain.repository

import me.elrevin.domain.model.Location

interface LocationRepository {
    suspend fun saveLocation(location: Location)
}