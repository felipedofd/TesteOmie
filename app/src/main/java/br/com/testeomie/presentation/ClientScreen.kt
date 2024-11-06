package br.com.testeomie.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.testeomie.R
import br.com.testeomie.model.Product
import br.com.testeomie.presentation.components.TextFieldComponent
import br.com.testeomie.presentation.components.item.ProductListItem

@Composable
fun ClientScreen(
    clientScreenViewModel: ClientScreenViewModel,
    onFinish: () -> Unit
) {
    val showDialog = remember { mutableStateOf(false) }
    val isProductListNotEmpty = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(8.dp))
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val orderNumber = clientScreenViewModel.orderNumberFlow.collectAsState().value
            Text("Ordem n° #$orderNumber", modifier = Modifier.align(Alignment.End))
            Text("Formulário de Venda")

            var clientName by remember { mutableStateOf(value = "") }

            TextFieldComponent(
                modifier = Modifier.fillMaxWidth(), value = clientName, onValueChange = {
                    clientName = it
                }, label = "Nome do Cliente", maxLines = 1, keyboardType = KeyboardType.Text
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(enabled = clientName.isNotEmpty(), onClick = {
                    showDialog.value = true
                }) {
                    Text("Incluir Produtos")
                }
                Button(enabled = isProductListNotEmpty.value, onClick = {
                    clientScreenViewModel.onSellSaveClick(clientName)
                    onFinish()
                }) {
                    Text("Salvar Venda")
                }
            }

            val salesList = clientScreenViewModel.productFlow.collectAsState(listOf()).value
            isProductListNotEmpty.value = salesList.isNotEmpty()
            if (salesList.isNotEmpty()) {
                LazyColumn {
                    items(salesList) { item ->
                        ProductListItem(item)
                    }
                }
            } else {
                Text(
                    text = stringResource(R.string.empty_product_list_text)
                )
            }
        }

        if (showDialog.value) {
            ProductDialogScreen(
                onSaveClick = { description: String, quantity: Int, value: Double ->
                    clientScreenViewModel.addProductToList(
                        Product(
                            description,
                            quantity,
                            value
                        )
                    )
                },
                onDismiss = {
                    showDialog.value = false
                })
        }
    }
}

@Composable
@Preview
fun SellingBottomSheetScreenPreview() {
//    SellingBottomSheetScreen {}
}