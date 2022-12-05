package com.example.space_news_compose.domain.remote.remoteds

import com.example.space_news_compose.domain.model.SpaceModelItem
import com.example.space_news_compose.util.Resource

interface RemoteDataSourceInterface {
    suspend fun getData(): Resource<ArrayList<SpaceModelItem>>
}