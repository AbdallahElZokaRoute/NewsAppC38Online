package com.route.domain.repos

import com.route.domain.entities.ArticlesItemDTO


interface NewsRepository {
    suspend fun getNewsData(sourceId: String): List<ArticlesItemDTO>
}

interface NewsOnlineDataSource {
    suspend fun getNewsFromAPI(sourceId: String): List<ArticlesItemDTO>
}

interface NewsOfflineDataSource {
    suspend fun getNewsFromDB(): List<ArticlesItemDTO>
    suspend fun saveNewsToDB(newsList: List<ArticlesItemDTO>)
}