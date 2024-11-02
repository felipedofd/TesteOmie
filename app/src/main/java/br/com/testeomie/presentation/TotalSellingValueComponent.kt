package br.com.testeomie.presentation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun TotalSellingValueComponent(totalSellingValue: Double, modifier: Modifier) {
    Text(
        text = "Total de vendas: R$$totalSellingValue",
        fontSize = 30.sp ,
        color = Color.DarkGray,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Left

    )
}

@Preview
@Composable
fun TotalSellingValueComponentPreview() {
    TotalSellingValueComponent(totalSellingValue = 27.45  , modifier = Modifier)
}