package com.example.suballigator.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.suballigator.entity.Content

@Dao
interface ContentDAO {

    @Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    suspend fun insert(content: Content)

    @Query("SELECT * FROM Content")
    suspend fun getAll(): List<Content>

    @Query("SELECT * FROM Content WHERE id = :contentId")
    suspend fun getContentById(contentId: Int): Content

    @Query("SELECT * FROM Content WHERE sessionId = :sessionId")
    suspend fun getContentBySessionId(sessionId: Int): List<Content>

    @Query("DELETE FROM Content")
    suspend fun deleteAll()
}