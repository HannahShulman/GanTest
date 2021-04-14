package com.hanna.shulman.gantest.data.datasource.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

//TESTS - V
class StringListConverter {

    @TypeConverter
    fun listToString(list: List<String>): String = Gson().toJson(list)

    @TypeConverter
    fun stringToList(value: String): List<String> {
        val listType = object : TypeToken<ArrayList<String>>() {}.type
        return Gson().fromJson(value, listType)
    }
}