package ru.narfu.cafeteria.ui.building

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import ru.narfu.cafeteria.databinding.FragmentBuildingBinding
import ru.narfu.cafeteria.db.model.Product

//отвечает за отображение данных и взаимодействие с
// пользователем на определенной части экрана приложения
class BuildingFragment : Fragment(), BuildingCallback {

    private lateinit var viewModel: BuildingViewModel
    private var _binding: FragmentBuildingBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val args: BuildingFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(BuildingViewModel::class.java) // Получаем экземпляр ViewModel

        _binding = FragmentBuildingBinding.inflate(inflater, container, false) // Надуваем разметку фрагмента

        val adapter = viewModel.getAdapter(args.building) // Получаем адаптер из ViewModel для текущего здания
        val layoutManager = (binding.recyclerView.layoutManager as GridLayoutManager)
        layoutManager.spanSizeLookup = object : SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return adapter.getSpanSize(position) // Устанавливаем правильное количество столбцов в зависимости от типа элемента
            }
        }
        binding.recyclerView.adapter = adapter // Устанавливаем адаптер для RecyclerView
        viewModel.setAdapterCallBack(this) // Устанавливаем колбэк для обратных вызовов

        return binding.root // Возвращаем корневой элемент разметки фрагмента
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        viewModel.setAdapterCallBack(null)
    }

    override fun onProductSelected(product: Product) {
        val action = BuildingFragmentDirections.actionOpenProduct(product) // Создаем действие навигации к продукту
        NavHostFragment.findNavController(this).navigate(action) // Навигируемся к выбранному продукту
    }

    override fun onProductUpdated(product: Product) {
        viewModel.updateProduct(product) // Обновляем продукт в ViewModel при его изменении
    }
}