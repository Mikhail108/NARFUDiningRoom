package ru.narfu.cafeteria.ui.cafeteria

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.narfu.cafeteria.App

class CafeteriaViewModel : ViewModel() {

    var adapter = CafeteriaAdapter()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val items = App.database.buildingDao().getAll()
            adapter.setItems(items)
        }
    }
}