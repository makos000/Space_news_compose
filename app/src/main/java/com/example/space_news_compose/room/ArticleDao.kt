package com.example.space_news_compose.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE )
    fun insertArticleToDB(beerEntity: ArticleEntity)

    @Query("SELECT * FROM article_table order by `index` DESC")
    fun readArticleFromDB(): Flow<List<ArticleEntity>>

    @Query("DELETE FROM article_table")
    fun nukeTable()
}