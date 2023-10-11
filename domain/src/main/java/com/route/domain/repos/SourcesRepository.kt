package com.route.domain.repos

import com.route.domain.entities.SourceItemDTO


interface SourcesRepository {
    suspend fun getSources(categoryId: String): List<SourceItemDTO>
}

interface OnlineSourcesDataSource {
    suspend fun getSourcesFromAPI(categoryId: String): List<SourceItemDTO>
}

interface OfflineSourcesDataSource {
    suspend fun getSourcesFromDB(): List<SourceItemDTO>
    suspend fun saveSourcesIntoDB(list: List<SourceItemDTO>)
}