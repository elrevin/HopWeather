package me.elrevin.presentation.base_ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import me.elrevin.presentation.R

val appFamily = FontFamily(
    Font(R.font.roboto_bold, FontWeight.Bold),
    Font(R.font.roboto, FontWeight.Normal),
    Font(R.font.roboto_medium, FontWeight.Medium),
)

object AppTypography {
    val Head = TextStyle(
        fontFamily = appFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 112.sp
    )

    val TinyHead = TextStyle(
        fontFamily = appFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 57.sp
    )

    val H1 = TextStyle(
        fontFamily = appFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 22.sp
    )

    val H2 = TextStyle(
        fontFamily = appFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp,
        lineHeight = 28.sp
    )

    val H3 = TextStyle(
        fontFamily = appFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        lineHeight = 24.sp
    )

    val H4 = TextStyle(
        fontFamily = appFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 24.sp
    )

    val Button = TextStyle(
        fontFamily = appFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 16.sp
    )

    val TextField = TextStyle(
        fontFamily = appFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 16.sp
    )

    val Title = TextStyle(
        fontFamily = appFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 18.sp
    )

    val Value = TextStyle(
        fontFamily = appFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        lineHeight = 20.sp
    )

    val LargeLabel = TextStyle(
        fontFamily = appFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp,
        lineHeight = 14.sp
    )

    val SmallLabel = TextStyle(
        fontFamily = appFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
        lineHeight = 11.sp
    )
}