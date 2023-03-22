package com.example.suballigator.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.suballigator.entity.Session

@Dao
interface SessionDAO {

    @Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    suspend fun insert(session: Session)

    @Query("SELECT * FROM Session")
    suspend fun getAll(): List<Session>

    @Query("SELECT * FROM Session WHERE deleted = false")
    suspend fun getAllNoDeleted(): List<Session>

    @Query("SELECT * FROM Session WHERE id = :session_id")
    suspend fun getSessionById(session_id: Int): Session

    @Query("DELETE FROM Session")
    suspend fun deleteAll()
}