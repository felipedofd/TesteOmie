package br.com.testeomie.presentation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
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
    Button(
        onClick = onClick,
        modifier = Modifier
            .size(56.dp)
            .clip(CircleShape),
        shape = CircleShape,
        contentPadding = PaddingValues(0.dp), // Remove padding interno
        colors = androidx.compose.material3.ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.omie_color)
        )
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