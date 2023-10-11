package com.route.data.sources

import com.route.data.database.SourcesDao
import com.route.data.models.SourceItem
import com.route.data.models.convertTo
import com.route.domain.entities.SourceItemDTO
import com.route.domain.repos.OfflineSourcesDataSource


class OfflineSourcesDataSourceImpl(val sourcesDao: SourcesDao) : OfflineSourcesDataSource {
    override suspend fun getSourcesFromDB(): List<SourceItemDTO> {
        try {
            return sourcesDao.getSourcesFromDB().map {
                it.convertTo(SourceItemDTO::class.java)
            }
        } catch (ex: Exception) {
            throw ex
        }
    }

    override suspend fun saveSourcesIntoDB(list: List<SourceItemDTO>) {
        try {
            sourcesDao.insertSourceToDB(list.map {
                it.convertTo(SourceItem::class.java)
            })
        } catch (ex: Exception) {
            throw ex
        }
    }
}