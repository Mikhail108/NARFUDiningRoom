package ru.narfu.cafeteria.db.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Building(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val image: String,
    val address: String
) : Parcelable {
    companion object {
        fun fromNumber(
            number: Int,
            address: String
        ) = Building(
            0,
            "АУК-$number",
            "file:///android_asset/$number.jpg",
            address
        )
    }
}