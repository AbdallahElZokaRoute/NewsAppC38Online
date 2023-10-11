package com.route.data.news

import com.route.data.Constants
import com.route.data.api.NewsServices
import com.route.data.models.convertTo
import com.route.domain.entities.ArticlesItemDTO
import com.route.domain.entities.NewsResponseDTO
import com.route.domain.repos.NewsOnlineDataSource

class NewsOnlineDataSourceImpl(val newsServices: NewsServices) : NewsOnlineDataSource {
    override suspend fun getNewsFromAPI(sourceId: String): List<ArticlesItemDTO> {
        try {
            return newsServices.getNewsBySource(Constants.API_KEY, sourceId)
                .convertTo(NewsResponseDTO::class.java).articles ?: listOf()
        } catch (ex: Exception) {
            throw ex
        }
    }

}