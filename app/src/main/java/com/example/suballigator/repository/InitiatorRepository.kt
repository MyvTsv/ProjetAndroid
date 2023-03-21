package com.example.suballigator.repository

import com.example.suballigator.dao.InitiatorDAO
import com.example.suballigator.entity.Initiator

class InitiatorRepository(private val initiatorDao: InitiatorDAO) {

    suspend fun insert(initiator: Initiator) = initiatorDao.insert(initiator)

    suspend fun insert(initiator: List<Initiator>) {
        for (initiator in initiator) {
            initiatorDao.insert(initiator)
        }
    }

    suspend fun getAll() = initiatorDao.getAll()

    suspend fun isExist(initiator: Initiator): Boolean {
        if (initiatorDao.getInitiatorById(initiator.id) != null) {
            return true
        }
        return false
    }

    suspend fun deleteAll() = initiatorDao.deleteAll()

}