package br.com.testeomie.model

import br.com.testeomie.data.DataSource
import kotlinx.coroutines.flow.Flow

class SalesRepository {

    private val dataSource = DataSource()

    fun saveSales(
        clientName: String,
        productDescription: String,
        productQuantity: String,
        unitaryProductValue: String
    ) {
        dataSource.saveSalesOnDb(
            clientName, productDescription, productQuantity, unitaryProductValue
        )
    }

    fun loadSales(): Flow<MutableList<Sales>> {
        return dataSource.loadSalesOnDb()
    }

    fun deleteSales(sales: String) {
        dataSource.deleteSalesDb(sales)
    }
}