package com.example.space_news_compose.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.space_news_compose.model.SpaceModelItem

@Entity(tableName = "article_table")
class ArticleEntity(val spaceModel: SpaceModelItem) {
    @PrimaryKey(autoGenerate = true)
    var index: Int = 0
}