package ru.narfu.cafeteria.db.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BuildingDao {
    @Query("SELECT * FROM building")
    fun getAll(): List<Building>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(users: List<Building>): List<Long>

    @Delete
    fun delete(user: Building)
}