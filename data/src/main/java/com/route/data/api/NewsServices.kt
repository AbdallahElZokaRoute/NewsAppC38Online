package com.route.data.api


import com.route.data.models.NewsResponse
import com.route.data.models.SourceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsServices {
    @GET("top-headlines/sources")
    suspend fun getNewsSources(
        @Query("apiKey") apiKey: String,
        @Query("category") category: String
    ): SourceResponse

    @GET("everything")
    suspend fun getNewsBySource(
        @Query("apiKey") apiKey: String,
        @Query("sources") sources: String,
    ): NewsResponse
}
