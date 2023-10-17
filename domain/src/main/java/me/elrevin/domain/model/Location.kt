package me.elrevin.domain.model

import javax.annotation.concurrent.Immutable

@Immutable
data class Location(
    val id: String,
    val name: String = "",
    val country: String = "",
    val region: String = "",
)
