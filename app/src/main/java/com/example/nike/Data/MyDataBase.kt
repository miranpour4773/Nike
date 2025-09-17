package com.example.nike.Data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Shoe :: class , Shopping_Card :: class , User :: class] , version =  1 , exportSchema = false)
@TypeConverters(Converter::class)
abstract class MyDataBase :RoomDatabase(){

    abstract val shoeDao:ShoeDao
    abstract val shopingDao:ShopingDao
    abstract val userDao: User_Dao

    companion object{
        @Volatile
        private var database : MyDataBase?= null

        fun getDataBase(context: Context): MyDataBase {

            if (database == null) {
                database = Room.databaseBuilder(
                    context.applicationContext,
                    MyDataBase::class.java,
                    "database.db"
                ).allowMainThreadQueries().build()
            }
            return database!!
        }
    }
}
