package com.example.space_news_compose.api

import com.example.space_news_compose.model.SpaceModelItem
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET(ApiRes.END_POINT)
    suspend fun getData(): Response<ArrayList<SpaceModelItem>>
}