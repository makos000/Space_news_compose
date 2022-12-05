package com.example.space_news_compose.domain.repo

import com.example.space_news_compose.data.local.room.ArticleEntity
import com.example.space_news_compose.domain.model.SpaceModelItem
import com.example.space_news_compose.util.Resource
import kotlinx.coroutines.flow.Flow

interface RepoInterface {

    suspend fun getData(): Flow<Resource<List<ArticleEntity>>>

    fun insertArticlesToDB(articleEntity: ArticleEntity)

    fun readArticlesFromDB(): Flow<List<ArticleEntity>>

    fun nukeTable()

    suspend fun getDataFromAPI() : Resource<ArrayList<SpaceModelItem>>
}