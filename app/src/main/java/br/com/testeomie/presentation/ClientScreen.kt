package br.com.testeomie.presentation

import android.annotation.SuppressLint
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
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import br.com.testeomie.R
import br.com.testeomie.model.Product
import br.com.testeomie.presentation.components.TextFieldComponent
import br.com.testeomie.presentation.components.item.ProductListItem


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ClientScreen(
    clientScreenViewModel: ClientScreenViewModel, onFinish: () -> Unit
) {
    val showDialog = remember { mutableStateOf(false) }
    val isProductListNotEmpty = remember { mutableStateOf(false) }

    Scaffold(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(8.dp))
        ) {
            Column(
                modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val orderNumber = clientScreenViewModel.orderNumberFlow.collectAsState().value
                Text(
                    text = stringResource(R.string.order_number_auto_increment_text, orderNumber),
                    modifier = Modifier.align(Alignment.End),
                    color = Color.White
                )
                Text(stringResource(R.string.sales_form_text), color = Color.White)

                var clientName by remember { mutableStateOf(value = "") }

                TextFieldComponent(
                    modifier = Modifier.fillMaxWidth(),
                    value = clientName,
                    onValueChange = {
                        clientName = it
                    },
                    label = stringResource(R.string.client_name_label),
                    maxLines = 1,
                    keyboardType = KeyboardType.Text
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        enabled = clientName.isNotEmpty(), onClick = {
                            showDialog.value = true
                        }, colors = ButtonColors(
                            containerColor = colorResource(R.color.omie_color),
                            contentColor = Color.White,
                            disabledContentColor = Color.DarkGray,
                            disabledContainerColor = Color.Gray
                        )
                    ) {
                        Text(stringResource(R.string.add_product_button_text))
                    }
                    if (isProductListNotEmpty.value) {
                        Button(
                            enabled = isProductListNotEmpty.value, onClick = {
                                clientScreenViewModel.onSellSaveClick(clientName)
                                onFinish()
                            }, colors = ButtonColors(
                                containerColor = colorResource(R.color.omie_color),
                                contentColor = Color.White,
                                disabledContentColor = Color.DarkGray,
                                disabledContainerColor = Color.Gray
                            )
                        ) {
                            Text(stringResource(R.string.save_sales_button_text))
                        }
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
                    Box(
                        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(R.string.empty_product_list_text),
                            color = Color.DarkGray
                        )
                    }
                }
            }

            if (showDialog.value) {
                ProductDialogScreen(onSaveClick = { description: String, quantity: Int, value: Double ->
                    clientScreenViewModel.addProductToList(
                        Product(
                            description, quantity, value
                        )
                    )
                }, onDismiss = {
                    showDialog.value = false
                })
            }
        }
    }
}
