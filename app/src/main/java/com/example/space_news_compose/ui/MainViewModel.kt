package com.example.space_news_compose.ui

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.example.space_news_compose.model.SpaceModelItem
import com.example.space_news_compose.repo.RepoInterface
import com.example.space_news_compose.room.ArticleEntity
import com.example.space_news_compose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val repoInterface: RepoInterface): ViewModel() {

    private val _data: MutableStateFlow<Resource<ArrayList<SpaceModelItem>>> = MutableStateFlow(Resource.Loading())
    var data: StateFlow<Resource<ArrayList<SpaceModelItem>>> = _data

    var readArticle: List<ArticleEntity> = listOf()

    var mainScreen = true

    var article = SpaceModelItem(listOf(),false,0,"N/A", listOf(),"N/A","N/A","N/A","N/A","N/A","N/A")


    fun getData(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _data.emit(Resource.Loading())
                val response = repoInterface.getData()
                _data.emit(handleResponse(response))
                response.body()?.let {
                    nukeData()
                    for (article in it){
                        insertIntoDatabase(article)
                    }
                }
            } catch (e: HttpException) {
                _data.emit(Resource.Error("Could not load articles"))
            } catch (e: IOException) {
                _data.emit(Resource.Error("Internet connection issue"))
            }
        }
    }

    fun readDatabase() = viewModelScope.launch(Dispatchers.IO){
        repoInterface.readArticlesFromDB().collect(){
            readArticle = it
        }
    }

    var databaseArticles = repoInterface.readArticlesFromDB()

    fun insertIntoDatabase(model: SpaceModelItem){
        val articleEntity = ArticleEntity(model)
        CoroutineScope(Dispatchers.IO).launch {
            repoInterface.insertArticlesToDB(articleEntity)
        }
    }


    fun nukeData(){
        CoroutineScope(Dispatchers.IO).launch {
            repoInterface.nukeTable()
        }
    }

    private fun handleResponse(response: Response<ArrayList<SpaceModelItem>>): Resource<ArrayList<SpaceModelItem>> {
        return if (response.isSuccessful) Resource.Success(response.body()!!)
        else Resource.Error(response.message())
    }
}