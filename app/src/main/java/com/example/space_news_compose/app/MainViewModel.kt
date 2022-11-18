package com.example.space_news_compose.app

import androidx.lifecycle.*
import com.example.space_news_compose.domain.model.SpaceModelItem
import com.example.space_news_compose.data.repo.RepoInterface
import com.example.space_news_compose.data.local.ArticleEntity
import com.example.space_news_compose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repoInterface: RepoInterface): ViewModel() {

    private var _data: MutableStateFlow<Resource<List<ArticleEntity>>> = MutableStateFlow(Resource.Loading())
    var data: StateFlow<Resource<List<ArticleEntity>>> = _data

    var mainScreen = true

    var article = SpaceModelItem(listOf(),false,0,"N/A", listOf(),"N/A","N/A","N/A","N/A","N/A","N/A")


    fun getData(){
        viewModelScope.launch(Dispatchers.IO) {
           repoInterface.getData().collect {
               _data.value = it
           }
        }
    }
}