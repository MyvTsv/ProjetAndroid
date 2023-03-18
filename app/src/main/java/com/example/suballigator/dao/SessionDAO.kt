package com.example.suballigator.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.suballigator.entity.Session

@Dao
interface SessionDAO {

    @Insert
    suspend fun insert(session: Session)

    @Query("SELECT * FROM Session")
    suspend fun getAll(): List<Session>

    @Query("SELECT * FROM Session WHERE sessionId = :session_id")
    suspend fun getSessionById(session_id: Int): Session

    @Query("DELETE FROM Session")
    suspend fun deleteAll()
}