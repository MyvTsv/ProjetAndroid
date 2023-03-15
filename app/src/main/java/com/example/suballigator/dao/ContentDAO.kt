package com.example.suballigator.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.suballigator.entity.Content

@Dao
interface ContentDAO {

    @Insert
    suspend fun insert(content: Content)

    @Query("SELECT * FROM Content")
    suspend fun getAll(): List<Content>
}