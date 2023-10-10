package me.elrevin.data.mapper

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun String.dateFormat(): String {
    val parts = this.split(' ')
    val date = parts[0].split('-').reversed().joinToString(separator = ".")
    return date + if (parts.size == 2) " ${parts[1]}" else ""
}

fun String.extractDate(): String {
    val parts = this.split(' ')
    return parts[0]
}

fun String.extractTime(): String {
    val parts = this.split(' ')
    return parts[1]
}

fun getIsoDate(): String {
    val current = LocalDateTime.now()
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    return current.format(formatter)
}