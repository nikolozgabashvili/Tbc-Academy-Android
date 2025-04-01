package com.example.tbcacademyhomework.presentation.designSystem

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun AppTextField(
    modifier: Modifier = Modifier,
    value: String,
    label: String? = null,
    enabled:Boolean = true,
    imeAction: ImeAction = ImeAction.Unspecified,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        maxLines = 1,
        enabled = enabled,
        keyboardOptions = KeyboardOptions(imeAction = imeAction),
        modifier = modifier,
        label = { label?.let { Text(label) } },
        value = TextFieldValue(text = value, selection = TextRange(value.length)),
        onValueChange = {
            onValueChange(it.text)
        }
    )
}


@Preview(showBackground = true)
@Composable
private fun AppTextFieldPrev() {
    MaterialTheme {
        AppTextField(
            modifier = Modifier.fillMaxWidth(),
            value = "",
            onValueChange = {}

        )
    }
}