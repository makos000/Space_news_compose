package com.example.space_news_compose.repo

import com.example.space_news_compose.model.SpaceModelItem
import com.example.space_news_compose.room.ArticleEntity
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface RepoInterface {

    suspend fun getData(): Response<ArrayList<SpaceModelItem>>

    fun insertArticlesToDB(articleEntity: ArticleEntity)

    fun readArticlesFromDB(): Flow<List<ArticleEntity>>

    fun nukeTable()
}