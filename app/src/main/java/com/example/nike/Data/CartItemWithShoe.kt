package com.example.nike.Data

import androidx.room.Embedded

data class CartItemWithShoe(
    @Embedded val shoe: Shoe,
    val sizee: Int,
    val count: Int
)

