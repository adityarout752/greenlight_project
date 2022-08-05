package com.example.greenlighassignment.utils

import androidx.room.TypeConverter
import com.example.greenlighassignment.model.ModelData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SalesTypeConverter {
    var gson = Gson()

    @TypeConverter
    fun dataToString(modelData: ModelData): String {
        return gson.toJson(modelData)
    }

    @TypeConverter
    fun stringToData(data :String) : ModelData {
        val listType = object :TypeToken<ModelData>() {}.type
        return  gson.fromJson(data,listType)
    }
}