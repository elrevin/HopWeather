package me.elrevin.domain.model

data class Location(
    val id: String,
    val name: String = "",
    val country: String = "",
    val region: String = "",
)
