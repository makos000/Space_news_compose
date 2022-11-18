package com.example.space_news_compose.data.local

import androidx.room.TypeConverter
import com.example.space_news_compose.domain.model.SpaceModelItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RoomConverter {
    private var gson = Gson()

    @TypeConverter
    fun articlesToString(spaceModel: SpaceModelItem): String = gson.toJson(spaceModel)

    @TypeConverter
    fun stringToArticle(data: String): SpaceModelItem {
        val listType = object : TypeToken<SpaceModelItem>() {}.type
        return gson.fromJson(data, listType)
    }
}