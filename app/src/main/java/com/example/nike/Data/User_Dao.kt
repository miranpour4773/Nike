package com.example.nike.Data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface User_Dao {
    @Insert
    fun insertUser(user: User)

    @Query("Select * from user")
    fun getUsers():List<User>

    @Query("SELECT * FROM User WHERE email = :email or name = :email AND password = :password")
     fun login(email: String , password: String): User?

     @Query("Select * from User Where email=:Email or name = :Email")
     fun exist(Email:String):Boolean

     @Query("Select * From user where email = :email or name= :email")
     fun checkExist(email: String):User?

     @Update
     fun changePass(user: User)

}