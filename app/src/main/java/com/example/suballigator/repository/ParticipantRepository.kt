package com.example.suballigator.repository

import com.example.suballigator.dao.ParticipantDAO
import com.example.suballigator.entity.Participant

class ParticipantRepository(private val participantDao: ParticipantDAO) {

    suspend fun insert(participant: Participant) = participantDao.insert(participant)

    suspend fun insert(participant: List<Participant>) {
        for (participant in participant) {
            participantDao.insert(participant)
        }
    }

    suspend fun getAll() = participantDao.getAll()

    suspend fun isExist(participant: Participant): Boolean {
        if (participantDao.getParticipantById(participant.id) != null) {
            return true
        }
        return false
    }

    suspend fun deleteAll() = participantDao.deleteAll()

}