package br.com.testeomie.presentation.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun TextFieldComponent(
    value: String,
    onValueChange: (String) -> Unit,
    label: String, maxLines: Int,
    keyboardType: KeyboardType,
    modifier: Modifier
) {
    OutlinedTextField(
        value = value, onValueChange, modifier,
        label = { Text(text = label) },
        maxLines = maxLines,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        )
    )
}