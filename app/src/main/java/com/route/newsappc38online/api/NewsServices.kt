package com.route.newsappc38online.api

import com.route.newsappc38online.api.model.SourceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsServices {
    @GET("top-headlines/sources")
    fun getNewsSources(@Query("apiKey") apiKey: String): Call<SourceResponse>
}
