package com.example.space_news_compose.data.remote.api

import com.example.space_news_compose.domain.model.SpaceModelItem
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET(ApiRes.END_POINT)
    suspend fun getData(): Response<ArrayList<SpaceModelItem>>
}