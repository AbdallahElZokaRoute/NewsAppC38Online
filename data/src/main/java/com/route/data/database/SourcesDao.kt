package com.route.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.route.data.models.SourceItem

@Dao
interface SourcesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSourceToDB(list: List<SourceItem>)

    @Query("SELECT * FROM SourceItem")
    suspend fun getSourcesFromDB(): List<SourceItem>
}
