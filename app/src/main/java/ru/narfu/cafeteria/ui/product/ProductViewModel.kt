package ru.narfu.cafeteria.ui.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.narfu.cafeteria.App
import ru.narfu.cafeteria.db.model.Product

class ProductViewModel : ViewModel() {

    fun onUpdated(product: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            App.database.productDao().insert(product)
        }
    }
}