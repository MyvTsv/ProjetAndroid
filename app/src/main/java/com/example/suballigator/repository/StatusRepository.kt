package com.example.suballigator.repository

import com.example.suballigator.dao.StatusDAO
import com.example.suballigator.entity.Status

class StatusRepository(private val statusDao: StatusDAO) {

    suspend fun insert(status: Status) = statusDao.insert(status)

    suspend fun insert(status: List<Status>) {
        for (status in status) {
            statusDao.insert(status)
        }
    }

    suspend fun getAll() = statusDao.getAll()

    suspend fun getStatusById(status_id: Int) = statusDao.getStatusById(status_id)

    suspend fun isExist(status: Status): Boolean {
        if (statusDao.getStatusById(status.id) != null) {
            return true
        }
        return false
    }

    suspend fun deleteAll() = statusDao.deleteAll()

}