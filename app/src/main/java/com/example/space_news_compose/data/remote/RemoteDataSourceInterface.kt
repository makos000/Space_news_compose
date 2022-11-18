package com.example.space_news_compose.data.remote

import com.example.space_news_compose.domain.model.SpaceModelItem
import com.example.space_news_compose.util.Resource

interface RemoteDataSourceInterface {
    suspend fun getData(): Resource<ArrayList<SpaceModelItem>>
}