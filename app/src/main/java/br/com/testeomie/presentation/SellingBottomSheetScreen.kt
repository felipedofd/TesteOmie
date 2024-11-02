package br.com.testeomie.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun SellingBottomSheetScreen(onDismiss: () -> Unit) {
    Dialog(
        onDismissRequest = onDismiss, properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(8.dp))
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()), // Adiciona rolagem
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val orderNumber = 1
                Text("Ordem n° #$orderNumber", modifier = Modifier.align(Alignment.End))
                Text("Formulário de Venda")

                // Campos do formulário com rolagem
                TextField(modifier = Modifier.fillMaxWidth(),
                    value = "",
                    onValueChange = {},
                    label = { Text("Nome do Cliente") })
                TextField(modifier = Modifier.fillMaxWidth(),
                    value = "",
                    onValueChange = {},
                    label = { Text("Produto") })
                TextField(modifier = Modifier.fillMaxWidth(),
                    value = "",
                    onValueChange = {},
                    label = { Text("Quantidade") })
                TextField(modifier = Modifier.fillMaxWidth(),
                    value = "",
                    onValueChange = {},
                    label = { Text("Valor Unitario") })
                val isButtonEnabled = remember { mutableStateOf(false) }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(onClick = { }, enabled = isButtonEnabled.value) {
                        Text("Confirmar")
                    }
                    Button(onClick = { onDismiss() }) {
                        Text("Cancelar")
                    }
                }

            }
        }
    }
}

@Composable
@Preview
fun SellingBottomSheetScreenPreview() {
    SellingBottomSheetScreen {}
}