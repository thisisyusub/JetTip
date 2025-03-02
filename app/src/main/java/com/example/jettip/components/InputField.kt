package com.example.jettip.components

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp


@Preview
@Composable
fun InputField(
    modifier: Modifier = Modifier,
    valueState: MutableState<String> = remember { mutableStateOf("") },
    label: String = "Label",
    enabled: Boolean = true,
    isSingleLine: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default,
    leadingIcon: @Composable (() -> Unit)? = null,
) {
    TextField(
        modifier = modifier.height(IntrinsicSize.Min),
        value = valueState.value,
        onValueChange = { valueState.value = it },
        label = { Text(label) },
        enabled = enabled,
        singleLine = isSingleLine,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType, imeAction = imeAction
        ),
        textStyle = TextStyle(
            fontSize = 16.sp, // Adjust font size
            lineHeight = 16.sp // Reduce line height to minimize spacing
        ),
        keyboardActions = onAction,
        leadingIcon = leadingIcon,
    )
}
