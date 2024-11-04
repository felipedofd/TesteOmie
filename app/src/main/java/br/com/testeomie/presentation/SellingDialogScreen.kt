package br.com.testeomie.presentation

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import br.com.testeomie.presentation.components.TextFieldComponent
import br.com.testeomie.repository.SalesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun SellingBottomSheetScreen(onDismiss: () -> Unit) {

    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val salesRepository = SalesRepository()

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
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val orderNumber = 1
                Text("Ordem n° #$orderNumber", modifier = Modifier.align(Alignment.End))
                Text("Formulário de Venda")

                var clientName by remember { mutableStateOf(value = "") }
                var productDescription by remember { mutableStateOf(value = "") }
                var productQuantity by remember { mutableStateOf(value = "") }
                var unitaryProductValue by remember { mutableStateOf(value = "") }

                TextFieldComponent(
                    modifier = Modifier.fillMaxWidth(), value = clientName, onValueChange = {
                        clientName = it
                    }, label = "Nome do Cliente", maxLines = 1, keyboardType = KeyboardType.Text
                )
                TextFieldComponent(
                    modifier = Modifier
                        .height(120.dp)
                        .fillMaxWidth(),
                    value = productDescription,
                    onValueChange = {
                        productDescription = it
                    },
                    label = "Descrição do Produto",
                    maxLines = 3,
                    keyboardType = KeyboardType.Text
                )
                TextFieldComponent(
                    modifier = Modifier.fillMaxWidth(),
                    value = productQuantity,
                    onValueChange = {
                        productQuantity = it
                    }, label = "Quantidade",
                    maxLines = 1,
                    keyboardType = KeyboardType.Text
                )
                TextFieldComponent(
                    modifier = Modifier.fillMaxWidth(),
                    value = unitaryProductValue,
                    onValueChange = {
                        unitaryProductValue = it
                    },
                    label = "Valor Unitário",
                    maxLines = 1,
                    keyboardType = KeyboardType.Text
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    val isButtonEnabled = remember { mutableStateOf(true) }
                    Button(onClick = {
                        var message = true
                        scope.launch(Dispatchers.IO) {
                            if (clientName.isEmpty()) {
                                message = false
                            } else if (clientName.isNotEmpty() && productDescription.isNotEmpty() && productQuantity.isNotEmpty() && unitaryProductValue.isNotEmpty()) {
                                salesRepository.saveSales(clientName, productDescription, productQuantity, unitaryProductValue)
                                message = true
                            }
                        }
                        scope.launch(Dispatchers.Main) {
                            if (message) {
                                Toast.makeText(context, "Salvo!", Toast.LENGTH_SHORT).show()
                                onDismiss()
                            } else {
                                Toast.makeText(
                                    context, "Preencha todos os campos!", Toast.LENGTH_SHORT
                                ).show()
                            }
                        }

                    }, enabled = isButtonEnabled.value) {
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