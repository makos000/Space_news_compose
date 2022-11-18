package com.example.space_news_compose.data.repo

import com.example.space_news_compose.data.remote.api.ApiInterface
import com.example.space_news_compose.data.local.ArticleDao
import com.example.space_news_compose.data.local.ArticleEntity
import com.example.space_news_compose.data.remote.RemoteDataSourceImpl
import com.example.space_news_compose.data.remote.RemoteDataSourceInterface
import com.example.space_news_compose.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RepoImpl @Inject constructor(val remote: RemoteDataSourceImpl, val local: ArticleDao): RepoInterface {
    override suspend fun getData(): Flow<Resource<List<ArticleEntity>>> = flow {
        emit(Resource.Loading())
        val response = remote.getData()
        if (response is Resource.Success){
            nukeTable()
            for (article in response.data!!){
                insertArticlesToDB(ArticleEntity(article))
            }
            readArticlesFromDB().collect(){
                emit(Resource.Success(it))
            }
        }
        else {
            emit(Resource.Error(response.message!!))
        }
    }

    override fun insertArticlesToDB(articleEntity: ArticleEntity) {
        return local.insertArticleToDB(articleEntity)
    }

    override fun readArticlesFromDB(): Flow<List<ArticleEntity>> {
        return local.readArticleFromDB()
    }

    override fun nukeTable() {
        return local.nukeTable()
    }
}