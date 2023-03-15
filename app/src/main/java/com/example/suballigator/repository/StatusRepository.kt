package com.example.suballigator.repository

import com.example.suballigator.dao.StatusDAO
import com.example.suballigator.entity.Status

class StatusRepository(private val statusDao: StatusDAO) {

    suspend fun insert(status: Status) = statusDao.insert(status)

    suspend fun getAll() = statusDao.getAll()

}