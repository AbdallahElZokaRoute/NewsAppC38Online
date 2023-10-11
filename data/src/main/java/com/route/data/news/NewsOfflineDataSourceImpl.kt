package com.route.data.news

import com.route.data.database.NewsDao
import com.route.data.models.ArticlesItem
import com.route.data.models.convertTo
import com.route.domain.entities.ArticlesItemDTO
import com.route.domain.repos.NewsOfflineDataSource


class NewsOfflineDataSourceImpl(val newsDao: NewsDao) : NewsOfflineDataSource {
    override suspend fun getNewsFromDB(): List<ArticlesItemDTO> {
        try {
            return newsDao.getNewsFromDB().map {
                it.convertTo(ArticlesItemDTO::class.java)
            }
        } catch (ex: Exception) {
            throw ex
        }
    }

    override suspend fun saveNewsToDB(newsList: List<ArticlesItemDTO>) {
        try {
            newsDao.insertNewsToDB(newsList.map { it.convertTo(ArticlesItem::class.java) })
        } catch (ex: Exception) {
            throw ex
        }
    }

}