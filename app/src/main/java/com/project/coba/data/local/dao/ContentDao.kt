package com.project.coba.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.project.coba.data.local.entity.ContentEntity

@Dao
interface ContentDao {
    @Query("SELECT * FROM content")
    suspend fun getContent(): ContentEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg account: ContentEntity)

    @Query("DELETE FROM content")
    suspend fun deleteContent()
}