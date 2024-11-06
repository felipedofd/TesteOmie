package br.com.testeomie.presentation

import android.annotation.SuppressLint
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
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import br.com.testeomie.R
import br.com.testeomie.presentation.components.TextFieldComponent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProductDialogScreen(
    onSaveClick: (
        productDescription: String,
        productQuantity: Int,
        unitaryProductValue: Double
    ) -> Unit, onDismiss: () -> Unit
) {
    val context = LocalContext.current

    var productDescription by remember { mutableStateOf(value = "") }
    var productQuantity by remember { mutableIntStateOf(value = 0) }
    var unitaryProductValue by remember { mutableStateOf(value = "") }
    Surface {
        Dialog(
            onDismissRequest = onDismiss,
            properties = DialogProperties(usePlatformDefaultWidth = false)
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
                    TextFieldComponent(
                        modifier = Modifier
                            .height(120.dp)
                            .fillMaxWidth(),
                        value = productDescription,
                        onValueChange = {
                            productDescription = it
                        },
                        label = stringResource(R.string.product_description_label),
                        maxLines = 3,
                        keyboardType = KeyboardType.Text
                    )
                    TextFieldComponent(
                        modifier = Modifier.fillMaxWidth(),
                        value = productQuantity.toString(),
                        onValueChange = {
                            productQuantity = it.toInt()
                        },
                        label = stringResource(R.string.quantity_label),
                        maxLines = 1,
                        keyboardType = KeyboardType.Number
                    )
                    TextFieldComponent(
                        modifier = Modifier.fillMaxWidth(),
                        value = unitaryProductValue,
                        onValueChange = {
                            unitaryProductValue = it
                        },
                        label = stringResource(R.string.unit_value_label),
                        maxLines = 1,
                        keyboardType = KeyboardType.Decimal
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Button(
                            onClick = { onDismiss() },
                            colors = ButtonColors(
                                containerColor = colorResource(R.color.omie_color),
                                contentColor = Color.White,
                                disabledContentColor = Color.Gray,
                                disabledContainerColor = Color.Gray
                            )
                        ) {
                            Text(stringResource(R.string.cancel_button_text))
                        }
                        Button(enabled = productDescription.isNotEmpty(),
                            onClick = {
                                if (productDescription.isNotEmpty() && productQuantity >= 0 && unitaryProductValue.toDouble() > 0.0) {

                                    onSaveClick(
                                        productDescription,
                                        productQuantity,
                                        unitaryProductValue.toDouble()
                                    )
                                    onDismiss()
                                } else {
                                    Toast.makeText(context,
                                        context.getString(R.string.invalid_field_toast_text), Toast.LENGTH_SHORT)
                                        .show()
                                }
                            }, colors = ButtonColors(
                                containerColor = colorResource(R.color.omie_color),
                                contentColor = Color.White,
                                disabledContentColor = Color.DarkGray,
                                disabledContainerColor = Color.Gray
                            )
                        ) {
                            Text(stringResource(R.string.confir_button_text))
                        }
                    }
                }
            }
        }
    }
}