package br.com.testeomie.model

import br.com.testeomie.data.DataSource

class SalesRepository(private val dataSource: DataSource = DataSource()) {

    suspend fun loadSales(): MutableList<Sale> {
        return dataSource.loadSalesOnDb()
    }

    suspend fun deleteSalesByUUID(name: String) {
        dataSource.deleteSalesDb(name)
    }

    suspend fun saveClientWithProducts(clientName: String, productList: List<Product>) {
        dataSource.saveClientWithProducts(clientName, productList)
    }

    suspend fun getOrderCount(): Int {
        return dataSource.getOrderCount()
    }
}