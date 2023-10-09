package me.elrevin.core.other

fun lowerOrNull(value: Int?, reference: Int): Boolean {
    return value == null || value < reference
}