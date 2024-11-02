package br.com.testeomie.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen() {
    val showDialog = remember { mutableStateOf(false) }
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .padding(end = 30.dp, bottom = 30.dp)
                .fillMaxSize(), contentAlignment = Alignment.BottomEnd
        ) {
            SellButtonComponent(
                onClick = {
                    showDialog.value = true

                }, modifier = Modifier
            )
            if (showDialog.value){
                SellingBottomSheetScreen { showDialog.value = false }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 100.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            TotalSellingValueComponent(13.13, modifier = Modifier)
        }
    }
}


@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}