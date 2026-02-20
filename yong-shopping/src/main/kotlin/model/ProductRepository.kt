package com.example.com.model

object ProductRepository {
    private val products = mutableListOf(
        Product("http://www.naver.com", "naver", 30.00, 10, "this is korean website", "http://www.naver.com")
    )

    fun allProducts(): List<Product> = products

    fun productByName(name: String) = products.find {
        it.name.equals(name, ignoreCase = true)
    }

    fun addProduct(product: Product) {
        if (productByName(product.name) != null) {
            throw IllegalStateException("You can not add duplicate product name : ${product.name}")
        }
        products.add(product)
    }

    fun removeProduct(name: String): Boolean {
        return products.removeIf { it.name == name }
    }
}