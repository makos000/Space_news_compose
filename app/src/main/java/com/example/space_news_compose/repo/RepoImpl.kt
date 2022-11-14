package com.example.space_news_compose.repo

import com.example.space_news_compose.api.ApiInterface
import com.example.space_news_compose.model.SpaceModelItem
import com.example.space_news_compose.room.ArticleDao
import com.example.space_news_compose.room.ArticleEntity
import com.example.space_news_compose.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class RepoImpl @Inject constructor(val apiInterface: ApiInterface, val articleDao: ArticleDao): RepoInterface {
    override suspend fun getData(): Flow<Resource<List<ArticleEntity>>> = flow {
        try {
            emit(Resource.Loading())
            val response = apiInterface.getData()
            if (response.isSuccessful){
                nukeTable()
                response.body()?.forEach{ element -> insertArticlesToDB(ArticleEntity(element)) }
                readArticlesFromDB().collect(){
                    emit(Resource.Success(it))
                }
            }
            else{
                emit(Resource.Error(response.message()))
            }
        } catch (e: HttpException) {
            emit(Resource.Error("Could not load articles"))
        } catch (e: IOException) {
            emit(Resource.Error("Internet connection issue"))
            //below is not correct
            readArticlesFromDB().collect(){
                emit(Resource.Success(it))
            }
        }

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