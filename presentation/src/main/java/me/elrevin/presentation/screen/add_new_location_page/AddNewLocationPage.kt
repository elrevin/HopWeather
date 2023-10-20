package me.elrevin.presentation.screen.add_new_location_page

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import me.elrevin.domain.model.Location
import me.elrevin.presentation.base_ui.theme.AppTheme

@Composable
fun AddNewLocationPage(
    locationList: List<Location>,
    searchLocationInProgress: Boolean,
    searchText: String,
    onChangeSearchText: (String) -> Unit,
    onToggleLocation: (Location) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Your cities",
            modifier = Modifier
                .padding(top = 58.dp, bottom = 16.dp),
            style = AppTheme.typography.H1,
            color = AppTheme.colors.screenText
        )

        AppTextField(value = searchText, label = "Name of the city", onChangeSearchText)

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            if (searchLocationInProgress) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(32.dp)
                        )
                    }
                }
            }
            items(locationList.size) {
                LocationItem(
                    location = locationList[it],
                    onClick = {
                        onToggleLocation(locationList[it])
                    }
                )
            }
        }
    }
}

@Composable
private fun LocationItem(
    location: Location,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                indication = rememberRipple(),
                interactionSource = remember { MutableInteractionSource() },
                onClick = onClick
            )
            .background(AppTheme.colors.panelBackground, shape = AppTheme.shapes.medium)
            .padding(12.dp),
        contentAlignment = Alignment.CenterEnd
    ) {
        if (location.isTracked) {
            Box(
                modifier = Modifier
                    .size(28.dp)
                    .background(AppTheme.colors.iconsBackground, shape = RoundedCornerShape(50)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    AppTheme.icons.check(),
                    modifier = Modifier.size(16.dp),
                    contentDescription = "",
                    tint = AppTheme.colors.iconsForeground
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 40.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = location.name,
                style = AppTheme.typography.H3,
                color = AppTheme.colors.panelText
            )
            Text(
                text = (if (location.region.isNotEmpty()) location.region + ", " else "") + location.country,
                style = AppTheme.typography.H4,
                color = AppTheme.colors.panelText.copy(0.8f)
            )
        }
    }
}

@Composable
private fun AppTextField(
    value: String,
    label: String,
    onValueChange: (String) -> Unit
) {
    //val interactionSource = remember { MutableInteractionSource() }
    //val focused by interactionSource.collectIsFocusedAsState()
    CompositionLocalProvider(LocalTextSelectionColors provides LocalTextSelectionColors.current) {
        BasicTextField(
            value = value,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = onValueChange,
            textStyle = AppTheme.typography.TextField.copy(color = AppTheme.colors.textFieldValue),
            cursorBrush = SolidColor(AppTheme.colors.textFieldValue),
            singleLine = true,
            maxLines = 1,
            minLines = 1,
            //interactionSource = interactionSource,
            decorationBox = @Composable { innerTextField ->
                DecorationBox(
                    label = label,
                    value = value,
                    innerTextField
                )
            }
        )
    }
}

@Composable
private fun DecorationBox(
    label: String,
    value: String,
    innerTextField: @Composable () -> Unit
) {
    Row(
        modifier = Modifier
            .background(color = AppTheme.colors.textFieldBackground, shape = AppTheme.shapes.small)
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Box(modifier = Modifier.weight(1f)) {
            if (value.isEmpty()) {
                Text(
                    text = label,
                    style = AppTheme.typography.TextField,
                    color = AppTheme.colors.textFieldPlaceholder
                )
            }
            innerTextField()
        }
    }
}