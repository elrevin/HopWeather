package me.elrevin.presentation.base_ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp

object  AppShapes {
    const val largeRadius = 33
    const val mediumRadius = 16
    const val smallRadius = 14
    val large = RoundedCornerShape(largeRadius.dp)
    val medium = RoundedCornerShape(mediumRadius.dp)
    val small = RoundedCornerShape(smallRadius.dp)
}
