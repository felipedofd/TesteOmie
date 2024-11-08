package br.com.testeomie.presentation.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import br.com.testeomie.R

@Composable
fun TotalSellingValueComponent(totalSellingValue: Double, modifier: Modifier) {
    Text(
        text = stringResource(R.string.total_sales_value_text, totalSellingValue),
        fontSize = 30.sp,
        color = Color.DarkGray,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Left
    )
}