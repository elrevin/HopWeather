package me.elrevin.presentation.base_ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class AppColorScheme(
    // Buttons
    val buttonBackground: Color,
    val buttonText: Color,
    val buttonToggledBackground: Color,
    val buttonToggledText: Color,

    // TextField
    val textFieldBackground: Color,
    val textFieldPlaceholder: Color,
    val textFieldValue: Color,

    // Other
    val screenBackground: Color,
    val screenText: Color,
    val topBarBackground: Color,
    val expandedTopBarText: Color,
    val collapsedTopBarText: Color,
    val panelBackground: Color,
    val panelText: Color,
    val chanceBarBackground: Color,
    val chanceBarForeground: Color,
    val iconsBackground: Color,
    val iconsForeground: Color,
    val divider: Color,
)

val localAppColors = staticCompositionLocalOf {
    AppColorScheme(
        buttonBackground = Color.Unspecified,
        buttonText = Color.Unspecified,
        buttonToggledBackground = Color.Unspecified,
        buttonToggledText = Color.Unspecified,
        textFieldBackground = Color.Unspecified,
        textFieldPlaceholder = Color.Unspecified,
        textFieldValue = Color.Unspecified,
        screenBackground = Color.Unspecified,
        screenText = Color.Unspecified,
        topBarBackground = Color.Unspecified,
        expandedTopBarText = Color.Unspecified,
        collapsedTopBarText = Color.Unspecified,
        panelBackground = Color.Unspecified,
        panelText = Color.Unspecified,
        chanceBarBackground = Color.Unspecified,
        chanceBarForeground = Color.Unspecified,
        iconsBackground = Color.Unspecified,
        iconsForeground = Color.Unspecified,
        divider = Color.Unspecified,
    )
}
