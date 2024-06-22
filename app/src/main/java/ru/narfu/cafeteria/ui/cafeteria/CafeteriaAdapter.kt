package ru.narfu.cafeteria.ui.cafeteria

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.narfu.cafeteria.R
import ru.narfu.cafeteria.databinding.ItemBuildingBinding
import ru.narfu.cafeteria.databinding.ItemHeaderBinding
import ru.narfu.cafeteria.db.model.Building

class CafeteriaAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var callback: CafeteriaCallback? = null
    private var items = listOf<Building>() // Список зданий, отображаемых в адаптере

    // Создание ViewHolder в зависимости от типа представления
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 0) { // Тип 0 - заголовок
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemHeaderBinding.inflate(inflater, parent, false)
            return HeaderViewHolder(binding)
        } // Все остальные типы (1 и выше) - элементы зданий

        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBuildingBinding.inflate(inflater, parent, false)
        return BuildingViewHolder(binding)
    }

    // Привязка данных к ViewHolder в зависимости от его позиции
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) != 0) { // Игнорируем заголовок
            (holder as BuildingViewHolder).bind(items[position - 1], ::onSelected)
        }
    }

    // Получение типа элемента по его позиции
    override fun getItemViewType(position: Int): Int {
        return if (position == 0) 0 else 1
    }

    // Возвращает общее количество элементов в списке, включая заголовок
    override fun getItemCount(): Int {
        return items.size + 1
    }

    // Устанавливает новый список зданий и обновляет адаптер
    fun setItems(newItems: List<Building>) {
        notifyItemRangeRemoved(1, items.size + 1) // Удаляем старые данные (за исключением заголовка)
        items = newItems // Обновляем список зданий
        notifyItemRangeInserted(1, items.size + 1) // Вставляем новые данные (за исключением заголовка)
    }

    // Обработчик события выбора здания
    private fun onSelected(building: Building) {
        callback?.onBuildingSelected(building) // Вызываем метод обратного вызова, если он установлен
    }

    // ViewHolder для отображения заголовка
    class HeaderViewHolder(binding: ItemHeaderBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.headerTextView.setText(R.string.header_menu) // Устанавливаем текст заголовка
        }
    }

    // ViewHolder для отображения элемента здания
    class BuildingViewHolder(
        private val binding: ItemBuildingBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        // Привязка данных здания к ViewHolder
        fun bind(building: Building, onSelected: (building: Building) -> Unit) {
            binding.imageView.load(building.image)
            binding.titleTextView.text = building.name
            binding.addressTextView.text = building.address

            binding.root.setOnClickListener {
                onSelected(building) // Обрабатываем нажатие на элемент и вызываем обратный вызов
            }
        }
    }
}