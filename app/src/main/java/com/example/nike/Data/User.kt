package com.example.nike.Data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    val userId : Int ?= null,
    val name : String ,
    val email : String ,
    var password : String ,
):Parcelable
