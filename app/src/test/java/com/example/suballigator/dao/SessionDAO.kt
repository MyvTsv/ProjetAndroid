package com.example.suballigator.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.suballigator.entitee.Session

@Dao
interface SessionDAO {

    @Insert
    suspend fun insert(session: Session)

    @Query("SELECT * FROM Session")
    suspend fun getAll(): List<Session>
}