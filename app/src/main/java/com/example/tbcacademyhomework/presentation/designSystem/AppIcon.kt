package com.example.tbcacademyhomework.presentation.designSystem

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tbcacademyhomework.presentation.theme.AppTheme

@Composable
fun AppIcon(
    modifier: Modifier = Modifier,
    icon: ImageVector?,
    tint: Color = LocalContentColor.current,
    contentDesc: String? = null,
    enabled: Boolean = true,
    onClick: (() -> Unit)? = null
) {

    val isEnabled = enabled && onClick != null
    icon?.let {
        Box(
            modifier = modifier
                .padding(4.dp)
                .clickable(
                    enabled = isEnabled,
                    onClick = { onClick?.invoke() },
                    interactionSource = null,
                    indication = null
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(imageVector = icon, contentDescription = contentDesc, tint = tint)
        }
    }
}

@Preview
@Composable
private fun AppIconPrev() {
    AppTheme {
        AppIcon(
            icon = Icons.Default.Add,
            onClick = {}
        )
    }
}