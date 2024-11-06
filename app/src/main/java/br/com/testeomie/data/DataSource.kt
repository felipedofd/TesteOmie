package br.com.testeomie.data

import br.com.testeomie.model.Product
import br.com.testeomie.model.Sale
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import java.util.UUID


class DataSource {

    private val db = FirebaseFirestore.getInstance()

    suspend fun loadSalesOnDb(): MutableList<Sale> {
        val productList: MutableList<Sale> = mutableListOf()
        val querySnapShot = db.collection("sales").get().await()
        for (document in querySnapShot.documents) {
            val product = document.toObject(Sale::class.java)
            if (product != null) {
                productList.add(product)
            }
        }
        return productList
    }

    suspend fun deleteSalesDb(name: String) {
        db.collection("sales").document(name).delete().await()
    }

    suspend fun saveClientWithProducts(
        clientName: String,
        productList: List<Product>
    ) {
        val uuid = UUID.randomUUID().toString()
        val salesMap = hashMapOf(
            "uuid" to uuid,
            "name" to clientName,
            "products" to productList
        )

        db.collection("sales").document(uuid).set(salesMap).await()
    }

    suspend fun getOrderCount(): Int {
        val querySnapShot = db.collection("sales").get().await()
        return querySnapShot.size()
    }
}