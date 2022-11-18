package com.example.space_news_compose.data.remote

import com.example.space_news_compose.data.remote.api.ApiInterface
import com.example.space_news_compose.domain.model.SpaceModelItem
import com.example.space_news_compose.util.Resource
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val apiInterface: ApiInterface): RemoteDataSourceInterface{
    override suspend fun getData(): Resource<ArrayList<SpaceModelItem>> {
        try {
            val response = apiInterface.getData()
            return if (response.isSuccessful){
                if (response.body() != null){
                    Resource.Success(response.body()!!)
                } else{
                    Resource.Error("Response body is null")
                }
            }else{
                Resource.Error(response.message())
            }
        }catch (e: HttpException){
            return Resource.Error("Could not load")
        }
        catch (e: IOException){
            return Resource.Error("No internet connection established")
        }
    }
}