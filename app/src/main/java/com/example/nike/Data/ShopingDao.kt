package com.example.nike.Data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ShopingDao{

    @Insert
    fun insertAll(shop: Shopping_Card)

    @Query("select *from Shopping_Card where userId = :userid")
    fun getAll(userid: Int): List<Shopping_Card>

    @Delete
    fun deleteShoe(shop: Shopping_Card)

    @Update
    fun updateShoe(shop: Shopping_Card)

}
