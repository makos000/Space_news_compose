package com.example.space_news_compose.repo

import com.example.space_news_compose.model.SpaceModelItem
import com.example.space_news_compose.room.ArticleEntity
import com.example.space_news_compose.util.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface RepoInterface {

    suspend fun getData(): Flow<Resource<List<ArticleEntity>>>

    fun insertArticlesToDB(articleEntity: ArticleEntity)

    fun readArticlesFromDB(): Flow<List<ArticleEntity>>

    fun nukeTable()
}