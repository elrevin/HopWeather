package me.elrevin.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "location")
data class LocationEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val country: String,
    val region: String,
)
