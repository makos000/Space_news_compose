package com.example.space_news_compose.data.di

import android.content.Context
import androidx.room.Room
import com.example.space_news_compose.data.remote.api.ApiInterface
import com.example.space_news_compose.data.remote.api.ApiRes
import com.example.space_news_compose.data.repo.RepoImpl
import com.example.space_news_compose.data.repo.RepoInterface
import com.example.space_news_compose.data.local.ArticleDao
import com.example.space_news_compose.data.local.ArticleDatabase
import com.example.space_news_compose.data.remote.RemoteDataSourceImpl
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class Module {
    @Provides
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    fun httpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    fun okHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

    @Provides
    fun retrofitBuilder(gson: Gson, okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(ApiRes.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()

    @Provides
    fun getApiDetails(retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }

    @Singleton
    @Provides
    fun getRemoteDS(apiInterface: ApiInterface): RemoteDataSourceImpl {
        return RemoteDataSourceImpl(apiInterface)
    }


    @Provides
    fun getRepo(remote: RemoteDataSourceImpl, local: ArticleDao): RepoInterface {
        return RepoImpl(remote, local)
    }

    @Provides
    fun provideAcronymDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        ArticleDatabase::class.java,
        "ArticleDatabase",
    ).build()

    @Provides
    fun provideDao(database: ArticleDatabase) = database.articleDao()
}