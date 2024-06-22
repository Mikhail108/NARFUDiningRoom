package ru.narfu.cafeteria.ui.building

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.narfu.cafeteria.App
import ru.narfu.cafeteria.db.model.Building
import ru.narfu.cafeteria.db.model.Product

class BuildingViewModel : ViewModel() {
    private var adapter: BuildingAdapter? = null

    fun getAdapter(building: Building): BuildingAdapter {
        adapter?.let { return it } // Если адаптер уже существует, возвращаем его

        val newAdapter = BuildingAdapter(building) // Создаем новый адаптер для заданного здания

        // Загружаем данные асинхронно из базы данных
        viewModelScope.launch(Dispatchers.IO) {
            val items = App.database.productDao().getAllForBuilding(building.id)
            val types = App.database.foodTypeDao().getAll()
            withContext(Dispatchers.Main) {
                newAdapter.setItems(items, types)
            }
        }
        adapter = newAdapter // Сохраняем новый адаптер в поле класса
        return newAdapter // Возвращаем новый адаптер
    }

    fun setAdapterCallBack(callback: BuildingCallback?) {
        adapter?.callback = callback
    }

    fun updateProduct(product: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            App.database.productDao().insert(product) // Обновляем продукт в базе данных
        }
    }
}