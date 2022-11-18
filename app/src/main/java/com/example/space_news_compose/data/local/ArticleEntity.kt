package com.example.space_news_compose.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.space_news_compose.domain.model.SpaceModelItem

@Entity(tableName = "article_table")
class ArticleEntity(val spaceModel: SpaceModelItem) {
    @PrimaryKey(autoGenerate = true)
    var index: Int = 0
}