package com.example.space_news_compose.model


import com.example.space_news_compose.room.ArticleEntity
import com.google.gson.annotations.SerializedName

data class SpaceModelItem(
    @SerializedName("events")
    val events: List<Any>,
    @SerializedName("featured")
    val featured: Boolean,
    @SerializedName("id")
    val id: Int,
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("launches")
    val launches: List<Launche>,
    @SerializedName("newsSite")
    val newsSite: String,
    @SerializedName("publishedAt")
    val publishedAt: String,
    @SerializedName("summary")
    val summary: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("updatedAt")
    val updatedAt: String,
    @SerializedName("url")
    val url: String
)