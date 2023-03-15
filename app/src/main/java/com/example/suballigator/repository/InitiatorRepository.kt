package com.example.suballigator.repository

import com.example.suballigator.dao.InitiatorDAO
import com.example.suballigator.entity.Initiator

class InitiatorRepository(private val initiatorDao: InitiatorDAO) {

    suspend fun insert(initiator: Initiator) = initiatorDao.insert(initiator)

    suspend fun getAll() = initiatorDao.getAll()

}