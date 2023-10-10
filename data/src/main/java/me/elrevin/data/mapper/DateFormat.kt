package me.elrevin.data.mapper

fun String.dateFormat(): String {
    val parts = this.split(' ')
    val date = parts[0].split('-').reversed().joinToString(separator = ".")
    return date + if (parts.size == 2) " ${parts[1]}" else ""
}