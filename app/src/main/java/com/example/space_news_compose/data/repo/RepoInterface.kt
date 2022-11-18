package com.example.space_news_compose.data.repo

import com.example.space_news_compose.data.local.ArticleEntity
import com.example.space_news_compose.util.Resource
import kotlinx.coroutines.flow.Flow

interface RepoInterface {

    suspend fun getData(): Flow<Resource<List<ArticleEntity>>>

    fun insertArticlesToDB(articleEntity: ArticleEntity)

    fun readArticlesFromDB(): Flow<List<ArticleEntity>>

    fun nukeTable()
}