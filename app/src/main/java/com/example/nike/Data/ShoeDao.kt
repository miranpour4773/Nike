package com.example.nike.Data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ShoeDao {
    @Insert
    fun insertAll(ArrayList: ArrayList<Shoe>)
    @Query("select *from shoe")
    fun getAllShoes(): List<Shoe>

    @Update
    fun updateShoe(shoe: Shoe)
}


