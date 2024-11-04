package br.com.testeomie.data

import android.util.Log
import br.com.testeomie.model.Sales
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class DataSource {
    private val db = FirebaseFirestore.getInstance()
    private val _allSales = MutableStateFlow<MutableList<Sales>>(mutableListOf())
    private val allSales: StateFlow<MutableList<Sales>> = _allSales


    fun saveSalesOnDb(
        clientName: String,
        productDescription: String,
        productQuantity: String,
        unitaryProductValue: String
    ) {
        val salesMap = hashMapOf(
            "name" to clientName,
            "description" to productDescription,
            "quantity" to productQuantity,
            "value" to unitaryProductValue
        )

        db.collection("sales").document(clientName).set(salesMap).addOnCompleteListener {
            Log.d("FRA", "save success on firestore")
        }.addOnFailureListener {
            Log.d("FRA", "save error on firestore")
        }
    }

    fun loadSalesOnDb(): Flow<MutableList<Sales>> {
        val salesList: MutableList<Sales> = mutableListOf()
        db.collection("sales").get().addOnCompleteListener { querySnapShot ->
            if (querySnapShot.isSuccessful) {
                for (document in querySnapShot.result) {
                    val sales = document.toObject(Sales::class.java)
                    salesList.add(sales)
                    _allSales.value = salesList
                }
            }
        }
        return allSales
    }

}