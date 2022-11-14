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
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val repoInterface: RepoInterface): ViewModel() {

    private var _data: MutableStateFlow<Resource<List<ArticleEntity>>> = MutableStateFlow(Resource.Loading())
    var data: StateFlow<Resource<List<ArticleEntity>>> = _data

    var mainScreen = true

    var article = SpaceModelItem(listOf(),false,0,"N/A", listOf(),"N/A","N/A","N/A","N/A","N/A","N/A")


    fun getData(){
        viewModelScope.launch(Dispatchers.IO) {
           repoInterface.getData().collect(){
               _data.value = it
           }
        }
    }
}