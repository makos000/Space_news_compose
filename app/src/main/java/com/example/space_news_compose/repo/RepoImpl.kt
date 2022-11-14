package com.example.space_news_compose.repo

import com.example.space_news_compose.api.ApiInterface
import com.example.space_news_compose.model.SpaceModelItem
import com.example.space_news_compose.room.ArticleDao
import com.example.space_news_compose.room.ArticleEntity
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class RepoImpl @Inject constructor(val apiInterface: ApiInterface, val articleDao: ArticleDao): RepoInterface {
    override suspend fun getData(): Response<ArrayList<SpaceModelItem>> {
        return apiInterface.getData()
    }

    override fun insertArticlesToDB(articleEntity: ArticleEntity) {
        return articleDao.insertArticleToDB(articleEntity)
    }

    override fun readArticlesFromDB(): Flow<List<ArticleEntity>> {
        return articleDao.readArticleFromDB()
    }

    override fun nukeTable() {
        return articleDao.nukeTable()
    }

}