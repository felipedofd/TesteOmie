package br.com.testeomie.presentation.components.itemSalesList

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.testeomie.model.Sales
import br.com.testeomie.presentation.components.DeleteButtonComponent

@Composable
fun SalesListItem(
    position: Int, salesList: MutableList<Sales>
) {

    val clientName = salesList[position].clientName
    val productDescription = salesList[position].productDescription
    val productQuantity = salesList[position].productQuantity
    val unitaryProductValue = salesList[position].unitaryProductValue

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
                text = clientName.toString(), style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold, fontSize = 20.sp
                )
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Descrição: ${productDescription.toString()}",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "Quantidade: ${productQuantity.toString()}",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "Valor Unitário: ${unitaryProductValue.toString()}",
                style = MaterialTheme.typography.bodyMedium
            )
            DeleteButtonComponent(onClick = {}, Modifier.align(Alignment.End))

        }
    }
}

@Composable
@Preview
fun SalesListItemPreview() {
    SalesListItem(0, salesList = mutableListOf())

}