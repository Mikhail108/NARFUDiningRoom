package ru.narfu.cafeteria

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.narfu.cafeteria.db.AppDatabase
import ru.narfu.cafeteria.db.DatabaseInit

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        // Инициализация базы данных приложения
        database = AppDatabase.getInstance(applicationContext)

        // Проверка наличия базы данных
        if (!AppDatabase.isExist(applicationContext)) {
            // Если база данных не существует, запускаем инициализацию в фоновом режиме
            CoroutineScope(Dispatchers.IO).launch {
                DatabaseInit.init()
            }
        }
    }

    companion object {
        lateinit var database: AppDatabase // Статическое поле для доступа к базе данных из любой части приложения
    }
}