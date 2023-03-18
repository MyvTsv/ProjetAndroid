package com.example.suballigator.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.suballigator.entity.Status

@Dao
interface StatusDAO {

    @Insert
    suspend fun insert(status: Status)

    @Query("SELECT * FROM Status")
    suspend fun getAll(): List<Status>

    @Query("SELECT * FROM Status WHERE statusId = :status_id")
    suspend fun getStatusById(status_id: Int): Status

    @Query("DELETE FROM Status")
    suspend fun deleteAll()
}