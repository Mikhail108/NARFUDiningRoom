package ru.narfu.cafeteria.ui.building

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.narfu.cafeteria.R
import ru.narfu.cafeteria.databinding.ItemBuildingHeaderBinding
import ru.narfu.cafeteria.databinding.ItemHeaderBinding
import ru.narfu.cafeteria.databinding.ItemProductBinding
import ru.narfu.cafeteria.db.model.Building
import ru.narfu.cafeteria.db.model.FoodType
import ru.narfu.cafeteria.db.model.Product

//Управляет отображением данных в виде списка или сетки
class BuildingAdapter(val building: Building) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var callback: BuildingCallback? = null
    private var items = listOf<Item>()// Список элементов для отображения в RecyclerView

    // Создание ViewHolder в зависимости от типа элемента
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            0 -> {
                // Заголовок здания
                val binding = ItemBuildingHeaderBinding.inflate(inflater, parent, false)
                BuildingHeaderViewHolder(binding, building)
            }

            1 -> {
                // Обычный заголовок
                val binding = ItemHeaderBinding.inflate(inflater, parent, false)
                HeaderViewHolder(binding)
            }

            else -> {
                // Продукт
                val binding = ItemProductBinding.inflate(inflater, parent, false)
                ProductViewHolder(binding)
            }
        }
    }

    // Привязка данных к ViewHolder
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position == 0) return// Пропускаем позицию заголовка здания

        // Обработка разных типов элементов
        when (val item = items[position - 1]) {
            is HeaderItem -> (holder as HeaderViewHolder).bind(item.label)
            is ProductItem -> (holder as ProductViewHolder).bind(
                item.product,
                ::onSelected,
                ::onUpdated
            )
        }
    }

    // Возвращает тип элемента по его позиции
    override fun getItemViewType(position: Int): Int {
        if (position == 0) return 0

        return when (items[position - 1]) {
            is HeaderItem -> 1
            is ProductItem -> 2
        }
    }

    // Возвращает общее количество элементов, включая заголовок здания
    override fun getItemCount(): Int {
        return items.size + 1
    }

    // Возвращает количество столбцов, занимаемых элементом в сетке
    fun getSpanSize(position: Int): Int {
        return if (getItemViewType(position) > 1) {
            1 // Продукт занимает один столбец
        } else {
            2 // Заголовок здания и обычный заголовок занимают два столбца
        }
    }

    // Вызывается при выборе продукта пользователем
    private fun onSelected(product: Product) {
        callback?.onProductSelected(product)
    }

    // Вызывается при обновлении продукта пользователем
    private fun onUpdated(product: Product) {
        callback?.onProductUpdated(product)
    }

    // Устанавливает новый список продуктов и типов еды для отображения
    fun setItems(newItems: List<Product>, types: List<FoodType>) {
        notifyItemRangeRemoved(1, items.size + 1)// Удаляем текущие элементы

        // Группируем продукты по типам еды
        val typesIds = mutableListOf<Long>()
        val sortedItems = mutableListOf<MutableList<Product>>()

        for (item in newItems) {
            var itemTypeIndex = -1
            for ((index, typeId) in typesIds.withIndex()) {
                if (typeId == item.typeId) {
                    itemTypeIndex = index
                    break
                }
            }
            if (itemTypeIndex == -1) {
                itemTypeIndex = typesIds.size
                typesIds.add(item.typeId)
                sortedItems.add(mutableListOf())
            }
            sortedItems[itemTypeIndex].add(item)
        }

        // Формируем список элементов для RecyclerView
        val result = mutableListOf<Item>()

        for ((index, typeId) in typesIds.withIndex()) {
            var typeLabel = "???"
            for (type in types) {
                if (type.id == typeId) {
                    typeLabel = type.name
                    break
                }
            }
            result.add(HeaderItem(typeLabel))// Добавляем заголовок типа

            for (item in sortedItems[index]) {
                result.add(ProductItem(item)) // Добавляем продукты
            }
        }

        items = result // Обновляем список элементов
        notifyItemRangeInserted(1, items.size + 1) // Уведомляем адаптер о вставке новых элементов
    }

    // Интерфейс для элементов RecyclerView
    sealed interface Item

    // Элемент заголовка
    class HeaderItem(val label: String) : Item

    // Элемент продукта
    class ProductItem(val product: Product) : Item

    // ViewHolder для заголовка здания
    class BuildingHeaderViewHolder(
        binding: ItemBuildingHeaderBinding,
        building: Building
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.imageView.load(building.image)
            binding.nameChip.text = building.name
            binding.addressChip.text = building.address
        }
    }

    // ViewHolder для обычного заголовка
    class HeaderViewHolder(private val binding: ItemHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        // Привязываем данные заголовка
        fun bind(label: String) {
            binding.headerTextView.text = label // Устанавливаем текст заголовка
        }
    }

    // Привязываем данные продукта
    class ProductViewHolder(
        private val binding: ItemProductBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            product: Product,
            onSelected: (product: Product) -> Unit,
            onUpdated: (product: Product) -> Unit
        ) {
            binding.imageView.load(product.image)
            binding.titleTextView.text = product.name
            binding.priceTextView.apply {
                text = context.getString(R.string.price_format, product.price)
            }

            binding.cartCountTextView.text = product.countInCart.toString() // Устанавливаем количество продуктов в корзине
            // Обработчик добавления продукта в корзину
            binding.addButton.setOnClickListener {
                if (product.countInCart >= 10) return@setOnClickListener
                product.countInCart += 1
                binding.cartCountTextView.text = product.countInCart.toString()
                onUpdated(product) // Вызываем callback при обновлении продукта
            }

            // Обработчик удаления продукта из корзины
            binding.removeButton.setOnClickListener {
                if (product.countInCart <= 0) return@setOnClickListener
                product.countInCart -= 1
                binding.cartCountTextView.text = product.countInCart.toString()
                onUpdated(product) // Вызываем callback при обновлении продукта
            }

            // Обработчик нажатия на элемент продукта
            binding.root.setOnClickListener {
                onSelected(product) // Вызываем callback при выборе продукта
            }
        }
    }
}