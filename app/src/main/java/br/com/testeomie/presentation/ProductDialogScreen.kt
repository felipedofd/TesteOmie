package br.com.testeomie.presentation

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import br.com.testeomie.presentation.components.TextFieldComponent

@Composable
fun ProductDialogScreen(
    onSaveClick: (
        productDescription: String,
        productQuantity: Int,
        unitaryProductValue: Double
    ) -> Unit,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current

    var productDescription by remember { mutableStateOf(value = "") }
    var productQuantity by remember { mutableIntStateOf(value = 0) }
    var unitaryProductValue by remember { mutableStateOf(value = "") }

    Dialog(
        onDismissRequest = onDismiss, properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White),
        ) {
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
                value = productQuantity.toString(),
                onValueChange = {
                    productQuantity = it.toInt()
                }, label = "Quantidade",
                maxLines = 1,
                keyboardType = KeyboardType.Number
            )
            TextFieldComponent(
                modifier = Modifier.fillMaxWidth(),
                value = unitaryProductValue,
                onValueChange = {
                    unitaryProductValue = it
                },
                label = "Valor Unitário",
                maxLines = 1,
                keyboardType = KeyboardType.Decimal
            )
            Row {
                Button(onClick = { onDismiss() }) {
                    Text("Cancelar")
                }
                Button(onClick = {
                    if (productDescription.isNotEmpty() && productQuantity > 0 && unitaryProductValue.toDouble() > 0.0) {
                        onSaveClick(
                            productDescription,
                            productQuantity,
                            unitaryProductValue.toDouble()
                        )
                        onDismiss()
                    } else {
                        Toast.makeText(context, "Campos inválidos", Toast.LENGTH_SHORT).show()
                    }
                }) {
                    Text("Confirmar")
                }
            }
        }
    }
}

@Preview
@Composable
fun ProductScreenPreview() {
//    ProductDialogScreen { _, _, _ -> }
}