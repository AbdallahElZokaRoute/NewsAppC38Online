package com.route.newsappc38online.widgets.news

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import com.route.newsappc38online.Constants
import com.route.newsappc38online.api.APIManager
import com.route.newsappc38online.api.model.ArticlesItem
import com.route.newsappc38online.api.model.NewsResponse
import com.route.newsappc38online.api.model.SourceItem
import com.route.newsappc38online.api.model.SourceResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel : ViewModel() {

    val sourcesList = mutableStateOf<List<SourceItem>>((listOf()))

    val selectedIndex = mutableIntStateOf(0)
    val newsList = mutableStateOf<List<ArticlesItem>?>(listOf())


    fun getNewsBySource(
        sourceItem: SourceItem,
        newsResponseState: MutableState<List<ArticlesItem>?>
    ) {
        APIManager
            .getNewsServices()
            .getNewsBySource(Constants.API_KEY, sourceItem.id ?: "")
            .enqueue(object : Callback<NewsResponse> {
                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {
                    val newsResponse = response.body()
                    newsResponseState.value = newsResponse?.articles

                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {

                }


            })

    }

    fun getNewsSources(category: String?, sourcesList: MutableState<List<SourceItem>>) {
        APIManager
            .getNewsServices()
            .getNewsSources(Constants.API_KEY, category = category ?: "")
//                    .execute()
            .enqueue(object : Callback<SourceResponse> {
                override fun onResponse(
                    call: Call<SourceResponse>,
                    response: Response<SourceResponse>
                ) {
                    val body = response.body()
                    Log.e("TAG", "onResponse: ${body?.status}")
                    Log.e("TAG", "onResponse: ${body?.sources}")
                    sourcesList.value = body?.sources ?: listOf()
                }

                override fun onFailure(call: Call<SourceResponse>, t: Throwable) {

                }

            })
    }
}