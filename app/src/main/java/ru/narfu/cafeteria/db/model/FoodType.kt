package ru.narfu.cafeteria.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FoodType(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
) {
    companion object {
        fun fromName(name: String) = FoodType(0, name)
    }
}