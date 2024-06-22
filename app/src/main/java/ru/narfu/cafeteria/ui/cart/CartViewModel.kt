package ru.narfu.cafeteria.ui.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.narfu.cafeteria.App
import ru.narfu.cafeteria.db.model.Product

class CartViewModel : ViewModel() {
    var adapter = CartAdapter()
    private var items = listOf<Product>()

    /**
     * Функция для обновления содержимого корзины.
     * Получает данные о продуктах в корзине из базы данных в фоновом режиме.
     */
    fun updateContent() {
        viewModelScope.launch(Dispatchers.IO) {
            val newItems = App.database.productDao().getAllInCart() // Получение всех продуктов, находящихся в корзине

            if (newItems != items) { // Проверка, изменились ли данные в корзине
                items = newItems // Обновление списка продуктов в ViewModel
                withContext(Dispatchers.Main) {
                    adapter.setItems(items) // Установка нового списка продуктов в адаптер RecyclerView на главном потоке
                }
            }
        }
    }

    /**
     * Функция для обновления информации о конкретном продукте в базе данных.
     * Используется при изменении количества или других свойств продукта в корзине.
     */
    fun updateProduct(product: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            App.database.productDao().insert(product) // Обновление продукта в базе данных в фоновом режиме
        }
    }
}