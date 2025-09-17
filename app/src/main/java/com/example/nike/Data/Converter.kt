package com.example.nike.Data

import androidx.room.TypeConverter

class Converter {
    @TypeConverter
    fun fromString(value: String): List<String> {
        return value.split(",")
    }

    @TypeConverter
    fun fromArrayList(list: List<String>): String {
        return list.joinToString(",")
    }
}