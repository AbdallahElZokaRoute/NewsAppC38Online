package com.route.data.sources

import com.route.data.Constants
import com.route.data.api.NewsServices
import com.route.data.models.convertTo
import com.route.domain.entities.SourceItemDTO
import com.route.domain.entities.SourceResponseDTO
import com.route.domain.repos.OnlineSourcesDataSource


class OnlineSourcesDataSourceImpl(
    val newsServices: NewsServices
) : OnlineSourcesDataSource {
    override suspend fun getSourcesFromAPI(categoryId: String): List<SourceItemDTO> {
        try {
            return newsServices.getNewsSources(Constants.API_KEY, categoryId)
                .convertTo(SourceResponseDTO::class.java).sources ?: listOf()
        } catch (ex: Exception) {
            throw ex
        }
    }


}