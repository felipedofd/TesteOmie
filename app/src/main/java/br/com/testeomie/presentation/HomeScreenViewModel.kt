package br.com.testeomie.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.testeomie.model.Sale
import br.com.testeomie.model.SalesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val salesRepository: SalesRepository = SalesRepository()
) : ViewModel() {

    init {
        onLoadSales()
    }

    private val _productFlow = MutableStateFlow<List<Sale>>(emptyList())
    internal val salesFlow = _productFlow.asStateFlow()


    fun onLoadSales() {
        viewModelScope.launch {
            val loadSalesFlow = salesRepository.loadSales()
            _productFlow.emit(loadSalesFlow)
        }
    }

    private suspend fun onDeleteSalesByUUID(uuid: String) {
        salesRepository.deleteSalesByUUID(uuid)
    }

    fun onConfirmDelete(uuid: String?) {
        if (uuid != null) {
            viewModelScope.launch {
                onDeleteSalesByUUID(uuid)
                onLoadSales()
            }
        }
    }
}