package br.com.testeomie.presentation.components.item

import android.app.AlertDialog
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.testeomie.model.Sale
import br.com.testeomie.presentation.components.DeleteButtonComponent

@Composable
fun SalesListItem(
    item: Sale,
    onConfirmDelete: (uuid: String?) -> Unit = {}
) {

    val context = LocalContext.current

    fun dialogAlertDeleting(
        onPositive: () -> Unit
    ) {
        val alertDialog = AlertDialog.Builder(context)
        alertDialog.setTitle("Deletar venda").setMessage("Deseja excluir a venda?")
            .setPositiveButton("Sim") { _, _ ->
                onPositive()

            }.setNegativeButton("Não") { _, _ ->

            }.show()
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "UUID: ${item.uuid}",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "Cliente: ${item.name}",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "Produtos:",
                style = MaterialTheme.typography.bodyMedium
            )
            item.products?.forEach { product ->
                ProductListItem(product)
            }
            DeleteButtonComponent(onClick = {
                dialogAlertDeleting(
                    onPositive = {
                        onConfirmDelete(item.uuid)
                    },
                )
            }, Modifier.align(Alignment.End))

        }
    }
}

@Composable
@Preview
fun SalesListItemPreview() {
//    SalesListItem(
//        Product(
//            "Bolo", "um", "200",
//        )
//    )

}