package com.hanna.shulman.gantest.data.datasource.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

//TESTS-V
class IntListConverter {

    @TypeConverter
    fun listToString(list: List<Int>): String = Gson().toJson(list)

    @TypeConverter
    fun stringToIntList(value: String): List<Int> {
        val listType = object : TypeToken<ArrayList<Int>>() {}.type
        return Gson().fromJson(value, listType)
    }
}