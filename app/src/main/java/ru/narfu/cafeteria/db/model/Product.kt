package ru.narfu.cafeteria.db.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Building::class,
            parentColumns = ["id"],
            childColumns = ["buildingId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = FoodType::class,
            parentColumns = ["id"],
            childColumns = ["typeId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Product(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val buildingId: Long,
    val name: String,
    val composition: String,
    val image: String,
    val price: Double,
    val typeId: Long,
    var countInCart: Int
) : Parcelable {
    companion object {
        fun fromData(
            building: Long,
            name: String,
            composition: String,
            imageNumber: String,
            price: Double,
            type: Long
        ) = Product(
            0,
            building,
            name,
            composition,
            "file:///android_asset/menu$imageNumber.jpg",
            price,
            type,
            0
        )
    }
}