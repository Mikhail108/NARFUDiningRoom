package ru.narfu.cafeteria.db.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProductDao {
    @Query("SELECT * FROM product")
    fun getAll(): List<Product>

    @Query("SELECT * FROM product WHERE buildingId = :buildingId")
    fun getAllForBuilding(buildingId: Long): List<Product>

    @Query("SELECT * FROM product WHERE countInCart > 0")
    fun getAllInCart(): List<Product>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(users: Product): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(users: List<Product>): List<Long>

    @Delete
    fun delete(user: Product)
}