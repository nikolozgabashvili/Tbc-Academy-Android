package com.example.tbcacademyhomework.presentation.designSystem

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun AppTextField(
    modifier: Modifier = Modifier,
    value: String,
    label: String? = null,
    keyboardType: KeyboardType = KeyboardType.Unspecified,
    startIcon: ImageVector? = null,
    enabled: Boolean = true,
    imeAction: ImeAction = ImeAction.Unspecified,
    onValueChange: (String) -> Unit
) {

    var textFieldValue by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue(text = value))
    }

    LaunchedEffect(value) {
        if (textFieldValue.text != value) {
            textFieldValue = TextFieldValue(text = value)
        }
    }

    OutlinedTextField(
        enabled = enabled,
        leadingIcon = startIcon?.let { { AppIcon(icon = it, enabled = enabled) } },
        keyboardOptions = KeyboardOptions(imeAction = imeAction, keyboardType = keyboardType),
        modifier = modifier,
        label = { label?.let { Text(label) } },
        value = textFieldValue,
        onValueChange = {
            textFieldValue = it
            onValueChange(it.text)
        }
    )
}

@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier,
    value: String,
    startIcon: ImageVector? = null,
    label: String? = null,
    enabled: Boolean = true,
    imeAction: ImeAction = ImeAction.Unspecified,
    onValueChange: (String) -> Unit
) {
    var showPassword by rememberSaveable { mutableStateOf(false) }
    val visibilityIcon = if (showPassword) Icons.Rounded.Visibility else Icons.Rounded.VisibilityOff
    val visualTransformation =
        if (showPassword) VisualTransformation.None else PasswordVisualTransformation()

    var textFieldValue by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue(text = value))
    }

    LaunchedEffect(value) {
        if (textFieldValue.text != value) {
            textFieldValue = TextFieldValue(text = value)
        }
    }


    OutlinedTextField(
        enabled = enabled,
        leadingIcon = startIcon?.let { { AppIcon(icon = it) } },
        keyboardOptions = KeyboardOptions(
            imeAction = imeAction,
            keyboardType = KeyboardType.Password
        ),
        modifier = modifier,
        visualTransformation = visualTransformation,
        trailingIcon = {
            AppIcon(
                enabled = enabled,
                icon = visibilityIcon,
                onClick = { showPassword = !showPassword })
        },
        label = { label?.let { Text(label) } },
        value = textFieldValue,
        onValueChange = {
            textFieldValue = it
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