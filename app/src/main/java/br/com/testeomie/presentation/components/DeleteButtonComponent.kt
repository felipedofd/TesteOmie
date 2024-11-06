package br.com.testeomie.presentation.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import br.com.testeomie.R

@Composable
fun DeleteButtonComponent(onClick: () -> Unit, modifier: Modifier) {
    FloatingActionButton(
        onClick = onClick,
        containerColor = Color.Transparent,
        contentColor = Color.Black,
        modifier = modifier.size(20.dp)
    ) {
        Icon(
            imageVector = Icons.Filled.Delete,
            contentDescription = stringResource(R.string.exclude_button_content_description),
        )
    }
}