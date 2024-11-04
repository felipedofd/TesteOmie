package br.com.testeomie.presentation.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.testeomie.R

@Composable
fun SellButtonComponent(onClick: () -> Unit, modifier: Modifier) {
    FloatingActionButton(
        onClick = onClick,
        containerColor = colorResource(R.color.omie_color),
        modifier = Modifier
            .size(56.dp)
            .clip(CircleShape)
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "Iniciar Nova Venda",
            tint = Color.White // Cor do ícone
        )
    }
}

@Preview
@Composable
fun SellButtonComponentPreview() {
    SellButtonComponent(onClick = {}, Modifier)
}