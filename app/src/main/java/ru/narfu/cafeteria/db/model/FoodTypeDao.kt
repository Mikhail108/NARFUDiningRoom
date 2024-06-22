package ru.narfu.cafeteria.db.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FoodTypeDao {
    @Query("SELECT * FROM foodType")
    fun getAll(): List<FoodType>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(users: List<FoodType>): List<Long>

    @Delete
    fun delete(user: FoodType)
}