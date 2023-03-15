package com.example.suballigator.repository

import com.example.suballigator.dao.ParticipantDAO
import com.example.suballigator.entity.Participant

class ParticipantRepository(private val participantDao: ParticipantDAO) {

    suspend fun insert(participant: Participant) = participantDao.insert(participant)

    suspend fun getAll() = participantDao.getAll()

}