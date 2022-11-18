package com.example.space_news_compose.domain.model


import com.google.gson.annotations.SerializedName

data class SpaceModelItem(
    @SerializedName("events")
    val events: List<Any> = listOf(),
    @SerializedName("featured")
    val featured: Boolean = false,
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("imageUrl")
    val imageUrl: String = "",
    @SerializedName("launches")
    val launches: List<Launche> = listOf(),
    @SerializedName("newsSite")
    val newsSite: String = "",
    @SerializedName("publishedAt")
    val publishedAt: String = "",
    @SerializedName("summary")
    val summary: String = "",
    @SerializedName("title")
    val title: String = "",
    @SerializedName("updatedAt")
    val updatedAt: String = "",
    @SerializedName("url")
    val url: String = ""
)