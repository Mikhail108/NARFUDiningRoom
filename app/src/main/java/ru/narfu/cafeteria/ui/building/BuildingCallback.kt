package ru.narfu.cafeteria.ui.building

import ru.narfu.cafeteria.db.model.Product

interface BuildingCallback {
    fun onProductSelected(product: Product)
    fun onProductUpdated(product: Product)
}