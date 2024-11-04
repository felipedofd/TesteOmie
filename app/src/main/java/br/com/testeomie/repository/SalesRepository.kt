package br.com.testeomie.repository

import br.com.testeomie.data.DataSource
import br.com.testeomie.model.Sales
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
}