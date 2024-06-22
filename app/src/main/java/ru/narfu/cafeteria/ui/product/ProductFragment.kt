package ru.narfu.cafeteria.ui.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import coil.load
import ru.narfu.cafeteria.R
import ru.narfu.cafeteria.databinding.FragmentProductBinding

class ProductFragment : Fragment() {

    private var _binding: FragmentProductBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val args: ProductFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel = ViewModelProvider(this).get(ProductViewModel::class.java)

        _binding = FragmentProductBinding.inflate(inflater, container, false)

        val product = args.product // Получение выбранного продукта из аргументов

        // Заполнение данных о продукте в макете фрагмента
        binding.imageView.load(product.image)
        binding.titleTextView.text = product.name
        binding.compositionTextView.text = product.composition
        binding.priceTextView.apply {
            text = context.getString(R.string.price_format, product.price)
        }

        binding.cartCountTextView.text = product.countInCart.toString() // Отображение количества продукта в корзине
        // Обработчик нажатия на кнопку добавления продукта в корзину
        binding.addButton.setOnClickListener {
            if (product.countInCart >= 10) return@setOnClickListener
            product.countInCart += 1
            binding.cartCountTextView.text = product.countInCart.toString()
            viewModel.onUpdated(product)
        }

        binding.removeButton.setOnClickListener {
            if (product.countInCart <= 0) return@setOnClickListener
            product.countInCart -= 1
            binding.cartCountTextView.text = product.countInCart.toString()
            viewModel.onUpdated(product)
        }

        val root: View = binding.root
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}