package ru.narfu.cafeteria

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.narfu.cafeteria.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Нахождение контроллера навигации для главного фрагмента навигации
        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        // Нахождение BottomNavigationView в разметке
        val navView: BottomNavigationView = binding.navView
        // Установка слушателя выбора элементов в BottomNavigationView
        navView.setOnItemSelectedListener {
            // Проверка, был ли выбран элемент навигации
            if (NavigationUI.onNavDestinationSelected(it, navController)) {
                return@setOnItemSelectedListener true
            }

            onBackPressedDispatcher.onBackPressed()
            return@setOnItemSelectedListener false
        }
    }
}