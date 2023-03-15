package com.example.suballigator.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.suballigator.entity.Initiator

@Dao
interface InitiatorDAO {

    @Insert
    suspend fun insert(initiator: Initiator)

    @Query("SELECT * FROM Initiator")
    suspend fun getAll(): List<Initiator>

}