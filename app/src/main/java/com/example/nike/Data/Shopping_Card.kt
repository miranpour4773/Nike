package com.example.nike.Data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    "Shopping_Card", foreignKeys = [
    ForeignKey(entity = User::class, parentColumns = ["userId"], childColumns = ["userid"], onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE),
    ForeignKey(entity = Shoe::class, parentColumns = ["shoeId"], childColumns = ["shoeid"], onDelete = ForeignKey.CASCADE , onUpdate = ForeignKey.CASCADE),]
)
data class Shopping_Card(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val userid: Int,
    val shoeid: Int,
    val brand_Name: String,
    val shoe_Name: String,
    val gender: Boolean,
    var like: Boolean,
    val price: Int,
    val img_Shoe: Int,
    val sizee: Int,
    var count: Int
)
