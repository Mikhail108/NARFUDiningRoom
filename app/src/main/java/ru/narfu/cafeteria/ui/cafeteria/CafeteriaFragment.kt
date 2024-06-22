package ru.narfu.cafeteria.ui.cafeteria

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import ru.narfu.cafeteria.databinding.FragmentCafeteriaBinding
import ru.narfu.cafeteria.db.model.Building

class CafeteriaFragment : Fragment(), CafeteriaCallback {

    private lateinit var viewModel: CafeteriaViewModel
    private var _binding: FragmentCafeteriaBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(CafeteriaViewModel::class.java) // Инициализация ViewModel

        _binding = FragmentCafeteriaBinding.inflate(inflater, container, false) // Создание экземпляра привязки

        binding.recyclerView.adapter = viewModel.adapter // Установка адаптера RecyclerView из ViewModel
        viewModel.adapter.callback = this // Установка текущего фрагмента в качестве колбэка в адаптере

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        viewModel.adapter.callback = null
    }

    override fun onBuildingSelected(building: Building) {
        val action = CafeteriaFragmentDirections.actionOpenBuilding(building) // Создание действия навигации с аргументом здания
        NavHostFragment.findNavController(this).navigate(action) // Навигация к другому фрагменту с передачей аргумента
    }
}