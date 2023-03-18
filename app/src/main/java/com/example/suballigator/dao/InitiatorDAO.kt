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

    @Query("SELECT * FROM Initiator WHERE initiatorId = :initiator_id")
    suspend fun getInitiatorById(initiator_id: Int): Initiator

    @Query("DELETE FROM Initiator")
    suspend fun deleteAll()

}