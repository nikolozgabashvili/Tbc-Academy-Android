package com.example.tbcacademyhomework.presentation.designSystem

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.tbcacademyhomework.R

@Composable
fun AppCheckBox(
    modifier: Modifier = Modifier,
    checked: Boolean,
    enabled: Boolean = true,
    enabledColor: Color = Color.Black,
    disabledColor: Color = Color.Gray,
    checkMarkColor: Color = Color.White,
    onCheckBoxClick: () -> Unit
) {

    val txtColor = if (enabled) enabledColor else disabledColor
    val colors = CheckboxDefaults.colors().copy(
        disabledCheckedBoxColor = disabledColor,
        disabledBorderColor = disabledColor,
        disabledUncheckedBorderColor = disabledColor,
        checkedCheckmarkColor = checkMarkColor,
        checkedBoxColor = enabledColor,
        checkedBorderColor = enabledColor,
        uncheckedBorderColor = enabledColor,
    )
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.clickable(
            enabled = enabled,
            onClick = onCheckBoxClick
        )
    ) {
        Checkbox(

            enabled = enabled,
            colors = colors,
            checked = checked,
            onCheckedChange = { onCheckBoxClick() })
        Text(
            color = txtColor,
            text = stringResource(R.string.remember_me)
        )
    }

}


@Preview(showBackground = true)
@Composable
private fun CheckBoxPrev() {
    MaterialTheme {
        AppCheckBox(
            checked = true,
            enabled = true,
            onCheckBoxClick = {}
        )
    }
}