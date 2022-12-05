package com.example.space_news_compose.domain.local.localds

import com.example.space_news_compose.data.local.room.ArticleEntity
import kotlinx.coroutines.flow.Flow

interface LocalDSInterface {
    fun insertArticleToDB(articleEntity: ArticleEntity)

    fun readArticleFromDB(): Flow<List<ArticleEntity>>

    fun nukeTable()
}