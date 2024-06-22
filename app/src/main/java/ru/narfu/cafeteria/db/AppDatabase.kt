package ru.narfu.cafeteria.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.narfu.cafeteria.db.model.Building
import ru.narfu.cafeteria.db.model.BuildingDao
import ru.narfu.cafeteria.db.model.FoodType
import ru.narfu.cafeteria.db.model.FoodTypeDao
import ru.narfu.cafeteria.db.model.Product
import ru.narfu.cafeteria.db.model.ProductDao
import java.io.File

//Инициализация базы данных в приложении
@Database(entities = [Building::class, Product::class, FoodType::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun buildingDao(): BuildingDao
    abstract fun productDao(): ProductDao
    abstract fun foodTypeDao(): FoodTypeDao

    companion object {
        private const val DATABASE_NAME = "main"
        // Проверка существования базы данных по имени
        fun isExist(context: Context) : Boolean {
            return File(context.getDatabasePath(DATABASE_NAME).absolutePath).exists()
        }

        // Получение экземпляра базы данных
        fun getInstance(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java, DATABASE_NAME
            ).build()
        }
    }
}