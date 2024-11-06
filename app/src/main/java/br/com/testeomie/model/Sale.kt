package br.com.testeomie.model

data class Sale(
    val uuid: String? = null,
    val name: String? = null,
    val products: List<Product>? = null,
)
