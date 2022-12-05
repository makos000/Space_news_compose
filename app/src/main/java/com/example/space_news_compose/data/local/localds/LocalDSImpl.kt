package com.example.space_news_compose.data.local.localds

import com.example.space_news_compose.data.local.room.ArticleDao
import com.example.space_news_compose.data.local.room.ArticleEntity
import com.example.space_news_compose.domain.local.localds.LocalDSInterface
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDSImpl @Inject constructor(private val dao: ArticleDao): LocalDSInterface {
    override fun insertArticleToDB(articleEntity: ArticleEntity) {
        return dao.insertArticleToDB(articleEntity)
    }

    override fun readArticleFromDB(): Flow<List<ArticleEntity>> {
        return dao.readArticleFromDB()
    }

    override fun nukeTable() {
        return dao.nukeTable()
    }
}