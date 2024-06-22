package ru.narfu.cafeteria.ui.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.narfu.cafeteria.R
import ru.narfu.cafeteria.databinding.ItemCartProductBinding
import ru.narfu.cafeteria.databinding.ItemHeaderBinding
import ru.narfu.cafeteria.db.model.Product

class CartAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var callback: CartCallback? = null // Callback для обработки событий нажатия в RecyclerView
    private var items = listOf<Product>() // Список товаров, отображаемых в адаптере

    // Создание ViewHolder в зависимости от типа элемента (заголовок или товар)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 0) {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemHeaderBinding.inflate(inflater, parent, false)
            return HeaderViewHolder(binding)
        }

        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCartProductBinding.inflate(inflater, parent, false)
        return ProductViewHolder(binding)
    }

    // Привязка данных к ViewHolder в зависимости от их позиции
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) != 0) {
            (holder as ProductViewHolder).bind(items[position - 1], ::onSelected, ::onUpdated)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) 0 else 1
    }

    override fun getItemCount(): Int {
        return items.size + 1
    }

    // Вызывается при выборе товара пользователем
    private fun onSelected(product: Product) {
        callback?.onProductSelected(product)
    }

    // Вызывается при обновлении товара в корзине
    private fun onUpdated(product: Product) {
        val newItems = items.toMutableList()
        // Если количество товара в корзине меньше или равно 0, удаляем его из списка
        if (product.countInCart <= 0) {
            for ((index, item) in items.withIndex()) {
                if (item == product) {
                    newItems.removeAt(index)
                    items = newItems
                    notifyItemRemoved(index + 1) // Уведомляем адаптер об удалении элемента
                    break
                }
            }
        }
        callback?.onProductUpdated(product) // Уведомляем остальные компоненты о обновлении товара
    }

    // Установка нового списка товаров в адаптер
    fun setItems(newItems: List<Product>) {
        notifyItemRangeRemoved(1, items.size + 1)
        items = newItems
        notifyItemRangeInserted(1, items.size + 1)
    }

    // ViewHolder для отображения заголовка
    class HeaderViewHolder(binding: ItemHeaderBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.headerTextView.setText(R.string.header_cart) // Устанавливаем текст заголовка
        }
    }

    // ViewHolder для отображения товара в корзине
    class ProductViewHolder(
        private val binding: ItemCartProductBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        // Привязка данных товара к ViewHolder
        fun bind(product: Product, onSelected: (Product) -> Unit, onUpdated: (Product) -> Unit) {
            binding.imageView.load(product.image)
            binding.titleTextView.text = product.name
            binding.priceTextView.apply {
                text = context.getString(R.string.price_format, product.price)
            }

            binding.cartCountTextView.text = product.countInCart.toString() // Установка количества товара в корзине
            // Обработчик нажатия на кнопку "Добавить в корзину"
            binding.addButton.setOnClickListener {
                if (product.countInCart >= 10) return@setOnClickListener // Ограничение на количество товаров в корзине
                product.countInCart += 1
                binding.cartCountTextView.text = product.countInCart.toString() // Обновление отображаемого количества
                onUpdated(product)
            }

            // Обработчик нажатия на кнопку "Убрать из корзины"
            binding.removeButton.setOnClickListener {
                if (product.countInCart <= 0) return@setOnClickListener // Защита от отрицательного количества товаров
                product.countInCart -= 1
                binding.cartCountTextView.text = product.countInCart.toString()
                onUpdated(product)
            }

            binding.root.setOnClickListener {
                onSelected(product)
            }
        }
    }
}