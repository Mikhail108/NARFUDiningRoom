package ru.narfu.cafeteria.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import ru.narfu.cafeteria.databinding.FragmentCartBinding
import ru.narfu.cafeteria.db.model.Product

class CartFragment : Fragment(), CartCallback {

    lateinit var viewModel: CartViewModel
    private var _binding: FragmentCartBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(CartViewModel::class.java)

        _binding = FragmentCartBinding.inflate(inflater, container, false)

        binding.recyclerView.adapter = viewModel.adapter
        viewModel.adapter.callback = this

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.updateContent() // Обновление содержимого ViewModel при восстановлении фрагмента
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        viewModel.adapter.callback = null
    }

    override fun onProductSelected(product: Product) {
        val action = CartFragmentDirections.actionOpenProduct(product)
        NavHostFragment.findNavController(this).navigate(action)
    }

    override fun onProductUpdated(product: Product) {
        viewModel.updateProduct(product)
    }
}