package com.example.space_news_compose.data.repo

import com.example.space_news_compose.data.local.room.ArticleEntity
import com.example.space_news_compose.data.local.localds.LocalDSImpl
import com.example.space_news_compose.data.remote.remoteds.RemoteDataSourceImpl
import com.example.space_news_compose.domain.repo.RepoInterface
import com.example.space_news_compose.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RepoImpl @Inject constructor(val remote: RemoteDataSourceImpl, val local: LocalDSImpl):
    RepoInterface {
    override suspend fun getData(): Flow<Resource<List<ArticleEntity>>> = flow {
        emit(Resource.Loading())
        readArticlesFromDB().collect(){
            if (it.isEmpty()){
                val response = getDataFromAPI()
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
            else{
                emit(Resource.Success(it))
            }
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

    override suspend fun getDataFromAPI() = remote.getData()

}