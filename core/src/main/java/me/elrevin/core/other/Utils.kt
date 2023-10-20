package me.elrevin.core.other

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

fun lowerOrNull(value: Int?, reference: Int): Boolean {
    return value == null || value < reference
}

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

fun String.extractHours(): Int {
    val parts = this.split(':')
    return parts[0].toInt()
}

fun getIsoDate(): String {
    val current = LocalDateTime.now()
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    return current.format(formatter)
}

fun getCurrentDateTimeWithMonthName(): String {
    val df = DateTimeFormatter.ofPattern("MMMM dd, HH:mm", Locale.US)
    return df.format(LocalDateTime.now())
}

fun String.toLocalDate(): LocalDate = LocalDate.parse(this)