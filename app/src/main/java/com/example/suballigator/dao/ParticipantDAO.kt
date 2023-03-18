package com.example.suballigator.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.suballigator.entity.Participant

@Dao
interface ParticipantDAO {

    @Insert
    suspend fun insert(participant: Participant)

    @Query("SELECT * FROM Participant")
    suspend fun getAll(): List<Participant>

    @Query("SELECT * FROM Participant WHERE participantId = :participant_id")
    suspend fun getParticipantById(participant_id: Int): Participant

    @Query("DELETE FROM Participant")
    suspend fun deleteAll()
}