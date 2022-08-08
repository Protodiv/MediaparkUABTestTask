package com.example.data.repository.newsrepository

import com.example.domain.models.NewsData
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Query

interface GNewsService {
    @GET("top-headlines")
    suspend fun getNews(
        @Query("token") apiKey: String = Common.API_KEY_GNEWS
    ) : NewsData

    @GET("search")
    suspend fun search(
        @Query("token") apiKey: String = Common.API_KEY_GNEWS,
        @Query("q") q: String,

        @Query("in") _in: String? = null,

        @Query("from") from: String? = null,
        @Query("to") to: String? = null,

        @Query("sortby") sortby: String? = null,
    ) : NewsData
}