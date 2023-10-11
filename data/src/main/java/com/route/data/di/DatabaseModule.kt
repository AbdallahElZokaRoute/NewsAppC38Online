package com.route.data.di

import android.content.Context
import androidx.room.Room
import com.route.data.database.NewsDao
import com.route.data.database.NewsLocalDatabase
import com.route.data.database.SourcesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideNewsDatabase(
        @ApplicationContext context: Context
    ): NewsLocalDatabase {
        return Room.databaseBuilder(context, NewsLocalDatabase::class.java, "News Local DB")
            .fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideNewsDAO(
        newsLocalDatabase: NewsLocalDatabase
    ): NewsDao {
        return newsLocalDatabase.getNewsDao()
    }

    @Singleton
    @Provides
    fun provideSourcesDAO(
        newsLocalDatabase: NewsLocalDatabase
    ): SourcesDao {
        return newsLocalDatabase.getSourcesDao()
    }

}