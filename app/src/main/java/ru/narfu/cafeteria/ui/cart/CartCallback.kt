package ru.narfu.cafeteria.ui.cart

import ru.narfu.cafeteria.db.model.Product

interface CartCallback {
    fun onProductSelected(product: Product)
    fun onProductUpdated(product: Product)
}