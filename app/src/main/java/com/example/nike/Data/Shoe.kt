package com.example.nike.Data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "Shoe")
data class Shoe(
    @PrimaryKey(autoGenerate = true)
    val shoeId: Int? = null,
    val brand_Name: String,
    val shoe_Name: String,
    val gender: Boolean,
    var like: Boolean,
    val price: Int,
    val img_Shoe: Int,
    val size: List<String>
) : Parcelable

