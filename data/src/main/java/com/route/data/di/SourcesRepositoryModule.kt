package com.route.data.di

import com.route.data.NetworkHandler
import com.route.data.api.NewsServices
import com.route.data.database.SourcesDao
import com.route.data.sources.OfflineSourcesDataSourceImpl
import com.route.data.sources.OnlineSourcesDataSourceImpl
import com.route.data.sources.SourcesRepositoryImpl
import com.route.domain.repos.OfflineSourcesDataSource
import com.route.domain.repos.OnlineSourcesDataSource
import com.route.domain.repos.SourcesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SourcesRepositoryModule {
    @Singleton
    @Provides
    fun provideSourcesRepository(
        onlineSourcesDataSource: OnlineSourcesDataSource,
        offlineSourcesDataSource: OfflineSourcesDataSource,
        networkHandler: NetworkHandler
    ): SourcesRepository {
        return SourcesRepositoryImpl(
            onlineSourcesDataSource,
            offlineSourcesDataSource,
            networkHandler
        )
    }

    @Singleton
    @Provides
    fun provideSourcesOnlineDataSource(
        newsServices: NewsServices
    ): OnlineSourcesDataSource {
        return OnlineSourcesDataSourceImpl(newsServices = newsServices)
    }

    @Singleton
    @Provides
    fun provideSourcesOfflineDataSource(
        sourcesDao: SourcesDao
    ): OfflineSourcesDataSource {
        return OfflineSourcesDataSourceImpl(sourcesDao)
    }
}