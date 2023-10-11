package com.route.data.di

import com.route.data.NetworkHandler
import com.route.data.api.NewsServices
import com.route.data.database.NewsDao
import com.route.data.news.NewsOfflineDataSourceImpl
import com.route.data.news.NewsOnlineDataSourceImpl
import com.route.data.news.NewsRepositoryImpl
import com.route.domain.repos.NewsOfflineDataSource
import com.route.domain.repos.NewsOnlineDataSource
import com.route.domain.repos.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NewsRepositoryModule {
    @Singleton
    @Provides
    fun provideNewsRepository(
        onlineDataSource: NewsOnlineDataSource,
        offlineDataSource: NewsOfflineDataSource,
        networkHandler: NetworkHandler
    ): NewsRepository {
        return NewsRepositoryImpl(
            onlineDataSource,
            offlineDataSource,
            networkHandler
        )
    }

    @Singleton
    @Provides
    fun provideNewsOnlineDataSource(newsServices: NewsServices): NewsOnlineDataSource {
        return NewsOnlineDataSourceImpl(newsServices)
    }

    @Singleton
    @Provides
    fun provideNewsOfflineDataSource(newsDao: NewsDao): NewsOfflineDataSource {
        return NewsOfflineDataSourceImpl(newsDao)
    }

    @Singleton
    @Provides
    fun provideNetworkHandler(): NetworkHandler {
        return object : NetworkHandler {
            override fun isOnline(): Boolean {
                return true
            }

        }
    }

}
