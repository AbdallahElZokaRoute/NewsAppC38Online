package com.route.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.route.data.models.ArticlesItem

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNewsToDB(list: List<ArticlesItem>)

    @Query("SELECT * FROM ArticlesItem")
    suspend fun getNewsFromDB(): List<ArticlesItem>
}