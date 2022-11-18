package com.example.space_news_compose.data.repo

import com.example.space_news_compose.data.local.ArticleEntity
import com.example.space_news_compose.data.remote.RemoteDataSourceImpl
import com.example.space_news_compose.domain.model.SpaceModelItem
import com.example.space_news_compose.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FakeRepo @Inject constructor(private val remote: RemoteDataSourceImpl): RepoInterface{

    private val spaceModelItem = SpaceModelItem()

    var list : MutableList<SpaceModelItem> = mutableListOf()

    override suspend fun getData(): Flow<Resource<List<ArticleEntity>>> = flow {
        emit(Resource.Loading())
        emit(Resource.Success(listOf(ArticleEntity(spaceModelItem))))
    }

    override fun insertArticlesToDB(articleEntity: ArticleEntity) {
        list.add(spaceModelItem)
    }

    override fun readArticlesFromDB(): Flow<List<ArticleEntity>> {
        return flow { list }
    }

    override fun nukeTable() {}

}