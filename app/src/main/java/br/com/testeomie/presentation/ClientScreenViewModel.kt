package br.com.testeomie.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.testeomie.model.Product
import br.com.testeomie.model.SalesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ClientScreenViewModel(
    private val salesRepository: SalesRepository = SalesRepository()
) : ViewModel() {

    init {
        getOrderNumber()
    }

    private val _orderNumberFlow = MutableStateFlow<Int>(0)
    internal val orderNumberFlow = _orderNumberFlow.asStateFlow()

    private val _productFlow = MutableStateFlow<List<Product>>(emptyList())
    internal val productFlow = _productFlow.asStateFlow()

    private val productList = mutableListOf<Product>()

    fun addProductToList(product: Product) {
        productList.add(product)

        viewModelScope.launch { _productFlow.emit(productList) }
    }

    fun onSellSaveClick(clientName: String) {
        viewModelScope.launch {
            salesRepository.saveClientWithProducts(clientName, productList)
            productList.clear()
        }
    }

    private fun getOrderNumber() {
        viewModelScope.launch {
            val orderCount = salesRepository.getOrderCount()
            _orderNumberFlow.emit(orderCount + 1)
        }
    }
}