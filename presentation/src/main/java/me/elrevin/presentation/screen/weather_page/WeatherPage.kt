package me.elrevin.presentation.screen.weather_page

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import coil.compose.AsyncImage
import com.airbnb.lottie.LottieProperty
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.airbnb.lottie.compose.rememberLottieDynamicProperties
import com.airbnb.lottie.compose.rememberLottieDynamicProperty
import kotlinx.coroutines.delay
import me.elrevin.core.other.getCurrentDateTimeWithMonthName
import me.elrevin.domain.model.Location
import me.elrevin.domain.model.Weather
import me.elrevin.presentation.R
import me.elrevin.presentation.base_ui.theme.AppTheme
import me.elrevin.presentation.base_ui.theme.ColorBlack
import java.time.LocalDateTime

@Composable
fun WeatherPage(location: Location, weather: Weather, loading: Boolean) {
    val state = rememberLazyListState()

    val maxTopBarHeight = 390f
    val minTopBarHeight = 200f
    var topBarHeight by remember {
        mutableFloatStateOf(maxTopBarHeight)
    }

    with(LocalDensity.current) {
        if (state.firstVisibleItemIndex == 0) {
            val d = state.firstVisibleItemScrollOffset.toDp().value
            topBarHeight = (maxTopBarHeight - d).coerceIn(minTopBarHeight, maxTopBarHeight)
        }
    }

    val imageAlpha = (topBarHeight - minTopBarHeight).coerceIn(0f, 30f) / 30f

    val topBarTextColor =
        if (imageAlpha > 0.3f) AppTheme.colors.expandedTopBarText else AppTheme.colors.collapsedTopBarText

    val isTopBarCollapsed = topBarHeight == minTopBarHeight

    val heightPercent = 1 - (topBarHeight - minTopBarHeight) / (maxTopBarHeight - minTopBarHeight)

    val currentTempFontSize = (
            AppTheme.typography.Head.fontSize.value -
                    heightPercent * (AppTheme.typography.Head.fontSize.value - AppTheme.typography.TinyHead.fontSize.value)
            ).sp

    val conditionIconSize = 128 - heightPercent * (128 - 64)

    val conditionTextVisible = heightPercent < 0.5f
    val dateVisible = heightPercent < 0.4f

    var activeTabIndex by remember {
        mutableIntStateOf(0)
    }


    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        if (isTopBarCollapsed) {
            CollapsedTopBar(
                location = location,
                weather = weather,
                minTopBarHeight = minTopBarHeight,
                topBarTextColor = topBarTextColor,
                imageAlpha = imageAlpha,
                currentTempFontSize = currentTempFontSize,
                conditionIconSize = conditionIconSize,
                conditionTextVisible = conditionTextVisible,
                activeTabIndex = activeTabIndex,
                onActiveTabChange = {
                    activeTabIndex = it
                },
                loading = loading
            )
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = state
        ) {
            item {
                ExpandedToolbar(
                    location = location,
                    weather = weather,
                    maxTopBarHeight = maxTopBarHeight,
                    imageAlpha = imageAlpha,
                    topBarHeight = topBarHeight,
                    topBarTextColor = topBarTextColor,
                    conditionIconSize = conditionIconSize,
                    currentTempFontSize = currentTempFontSize,
                    conditionTextVisible = conditionTextVisible,
                    dateVisible = dateVisible,
                    loading = loading
                )
            }
            item {
                TabRow(
                    activeTabIndex,
                    imageAlpha
                ) {
                    activeTabIndex = it
                }
            }
            if (weather.forecasts.isEmpty()) {
                item {
                    Box(modifier = Modifier.padding(16.dp), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(32.dp)
                        )
                    }
                }
            } else {
                when (activeTabIndex) {
                    0 -> DayForecast(
                        dayForecast = weather.forecasts[0],
                        nextDayForecast = weather.forecasts[1]
                    )

                    1 -> TomorrowForecast(
                        dayForecast = weather.forecasts[1],
                        nextDayForecast = weather.forecasts[2]
                    )

                    2 -> SevenDaysForecast(weather.forecasts)
                }

            }
        }
    }
}

@Composable
private fun CollapsedTopBar(
    location: Location,
    weather: Weather,
    minTopBarHeight: Float,
    topBarTextColor: Color,
    imageAlpha: Float,
    currentTempFontSize: TextUnit,
    conditionIconSize: Float,
    conditionTextVisible: Boolean,
    activeTabIndex: Int,
    onActiveTabChange: (Int) -> Unit,
    loading: Boolean
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height((minTopBarHeight + 74f).dp)
            .background(AppTheme.colors.topBarBackground)
            .zIndex(2f)
    ) {
        HeaderRow(textColor = topBarTextColor, location = location, alpha = imageAlpha, loading)
        CurrentWeatherRow(
            weather = weather,
            fontSize = currentTempFontSize,
            textColor = topBarTextColor,
            iconSize = conditionIconSize,
            conditionTextVisible = conditionTextVisible,
            alpha = imageAlpha
        )
        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.BottomCenter) {
            TabRow(
                activeTabIndex,
                imageAlpha,
                onActiveTabChange
            )
        }
    }
}

@Composable
private fun ExpandedToolbar(
    location: Location,
    weather: Weather,
    maxTopBarHeight: Float,
    imageAlpha: Float,
    topBarHeight: Float,
    topBarTextColor: Color,
    conditionIconSize: Float,
    currentTempFontSize: TextUnit,
    conditionTextVisible: Boolean,
    dateVisible: Boolean,
    loading: Boolean
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(maxTopBarHeight.dp)
            .background(AppTheme.colors.topBarBackground.copy(1 - imageAlpha))
            .clip(
                AppTheme.shapes.large.copy(
                    topStart = CornerSize(0),
                    topEnd = CornerSize(0),
                    bottomStart = CornerSize((AppTheme.shapes.largeRadius * imageAlpha).dp),
                    bottomEnd = CornerSize((AppTheme.shapes.largeRadius * imageAlpha).dp),
                )
            ),
        contentAlignment = Alignment.BottomCenter
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(topBarHeight.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxSize()
                    .alpha(imageAlpha),
                model = weather.currentWeather!!.conditionIllustration,
                contentDescription = "",
                contentScale = ContentScale.Crop,
                alignment = Alignment.BottomCenter
            )
            Column(
                modifier = Modifier.fillMaxSize(),
            ) {
                HeaderRow(
                    textColor = topBarTextColor,
                    location = location,
                    alpha = imageAlpha,
                    loading = loading
                )
                CurrentWeatherRow(
                    weather = weather,
                    fontSize = currentTempFontSize,
                    textColor = topBarTextColor,
                    iconSize = conditionIconSize,
                    conditionTextVisible = conditionTextVisible,
                    alpha = imageAlpha
                )
                DateRow(
                    weather = weather,
                    alpha = imageAlpha,
                    textColor = topBarTextColor,
                    dateVisible
                )
            }
        }
    }
}

@Composable
private fun HeaderRow(
    textColor: Color,
    location: Location,
    alpha: Float,
    loading: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 58.dp, start = 16.dp, end = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .weight(1f),
            text = "${location.name}, ${location.country}",
            style = AppTheme.typography.H1.copy(
                shadow = Shadow(
                    color = ColorBlack.copy(alpha),
                    blurRadius = 8f
                )
            ),
            color = textColor
        )

        if (loading) {
            val composition by rememberLottieComposition(
                if (LocalDateTime.now().hour in (6..18))
                    LottieCompositionSpec.RawRes(R.raw.day_loading)
                else
                    LottieCompositionSpec.RawRes(R.raw.night_loading)
            )


            val dynamicProperties = rememberLottieDynamicProperties(
                rememberLottieDynamicProperty(
                    property = LottieProperty.COLOR_FILTER,
                    value = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
                        textColor.hashCode(),
                        BlendModeCompat.SRC_ATOP
                    ),
                    keyPath = arrayOf("**")
                )
            )

            Text(text = "updating", style = AppTheme.typography.SmallLabel, color = textColor)
            LottieAnimation(
                composition = composition,
                iterations = LottieConstants.IterateForever,
                modifier = Modifier
                    .size(32.dp),
                dynamicProperties = dynamicProperties
            )
        }
    }
}

@Composable
private fun CurrentWeatherRow(
    weather: Weather,
    fontSize: TextUnit,
    textColor: Color,
    iconSize: Float,
    conditionTextVisible: Boolean,
    alpha: Float
) {
    val bottomPadding = with(LocalDensity.current) {
        (fontSize.toDp().value * 0.25)
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        Text(
            text = weather.currentWeather!!.temp.toString(),
            style = AppTheme.typography.Head.copy(
                fontSize = fontSize,
                lineHeight = (fontSize.value - 30).sp,
                shadow = Shadow(
                    color = ColorBlack.copy(alpha),
                    blurRadius = 8f
                )
            ),
            color = textColor
        )
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.BottomStart
        ) {
            Text(
                text = "°",
                style = AppTheme.typography.Head.copy(
                    fontSize = fontSize,
                    lineHeight = fontSize,
                    shadow = Shadow(
                        color = ColorBlack.copy(alpha),
                        blurRadius = 8f
                    )
                ),
                color = textColor
            )
            Text(
                modifier = Modifier.padding(bottom = bottomPadding.dp),
                text = "feels like ${weather.currentWeather!!.feelslike} °",
                style = AppTheme.typography.H4.copy(
                    shadow = Shadow(
                        color = ColorBlack.copy(alpha),
                        blurRadius = 8f
                    )
                ),
                color = textColor
            )
        }
        Column(
            modifier = Modifier.padding(bottom = bottomPadding.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                modifier = Modifier.size(iconSize.dp),
                model = weather.currentWeather!!.conditionIcon,
                contentScale = ContentScale.Fit,
                contentDescription = ""
            )
            Box(
                modifier = Modifier
                    .height((30f * iconSize / 128f).dp)
            ) {
                if (conditionTextVisible) {
                    Text(
                        text = weather.currentWeather!!.conditionText,
                        style = AppTheme.typography.H1.copy(
                            fontSize = (AppTheme.typography.H1.fontSize.value * iconSize / 128f).sp,
                            shadow = Shadow(
                                color = ColorBlack.copy(alpha),
                                blurRadius = 8f
                            )
                        ),
                        color = textColor
                    )
                }

            }
        }
    }
}

@Composable
private fun ColumnScope.DateRow(
    weather: Weather,
    alpha: Float,
    textColor: Color,
    visible: Boolean
) {
    AnimatedVisibility(
        modifier = Modifier.weight(1f),
        visible = visible,
        enter = slideInVertically(tween(300), initialOffsetY = { it / 2 }) + fadeIn(),
        exit = slideOutVertically(tween(300), targetOffsetY = { it / 2 }) + fadeOut()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            CurrentDateAndTime(textColor, alpha)
            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = "Day ${weather.dayTemp}°",
                    style = AppTheme.typography.H2.copy(
                        shadow = Shadow(
                            color = ColorBlack.copy(alpha),
                            blurRadius = 8f
                        )
                    ),
                    color = textColor
                )
                Text(
                    text = "Day ${weather.nightTemp}°",
                    style = AppTheme.typography.H2.copy(
                        shadow = Shadow(
                            color = ColorBlack.copy(alpha),
                            blurRadius = 8f
                        )
                    ),
                    color = textColor
                )
            }
        }
    }
}

@Composable
private fun CurrentDateAndTime(textColor: Color, alpha: Float) {
    var time by remember { mutableStateOf(getCurrentDateTimeWithMonthName()) }
    LaunchedEffect(0) {
        while (true) {
            time = getCurrentDateTimeWithMonthName()
            delay(60000)
        }
    }

    Text(
        text = time,
        style = AppTheme.typography.H2.copy(
            shadow = Shadow(
                color = ColorBlack.copy(alpha),
                blurRadius = 8f
            )
        ),
        color = textColor
    )
}

@Composable
private fun TabRow(
    activeIndex: Int,
    imageAlpha: Float,
    onIndexChange: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(AppTheme.colors.topBarBackground.copy(1f - imageAlpha))
            .padding(16.dp)
            .height(42.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TabButton(text = "Today", active = activeIndex == 0) {
            onIndexChange(0)
        }

        TabButton(text = "Tomorrow", active = activeIndex == 1) {
            onIndexChange(1)
        }

        TabButton(text = "7 days", active = activeIndex == 2) {
            onIndexChange(2)
        }
    }
}

@Composable
private fun RowScope.TabButton(
    text: String,
    active: Boolean,
    onClick: () -> Unit
) {
    val containerColor =
        if (active) AppTheme.colors.buttonToggledBackground else AppTheme.colors.buttonBackground
    val contentColor = if (active) AppTheme.colors.buttonToggledText else AppTheme.colors.buttonText
    Button(
        modifier = Modifier
            .height(42.dp)
            .weight(1f),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor,
        ),
        shape = AppTheme.shapes.small,
        contentPadding = PaddingValues(horizontal = 14.dp, vertical = 9.dp),
        onClick = onClick
    ) {
        Text(
            text = text,
            style = AppTheme.typography.Button
        )
    }

}