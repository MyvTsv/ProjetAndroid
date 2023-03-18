package com.example.suballigator.repository

import com.example.suballigator.dao.AptitudeDAO
import com.example.suballigator.entity.Aptitude

class AptitudeRepository(private val aptitudeDao: AptitudeDAO) {

    suspend fun insert(aptitude: Aptitude) = aptitudeDao.insert(aptitude)

    suspend fun insert(aptitude: List<Aptitude>) {
        for (aptitude in aptitude) {
            aptitudeDao.insert(aptitude)
        }
    }

    suspend fun getAll() = aptitudeDao.getAll()

    suspend fun isExist(aptitude: Aptitude): Boolean {
        if (aptitudeDao.getAptitudeById(aptitude.aptitudeId) != null) {
            return true
        }
        return false
    }

    suspend fun deleteAll() = aptitudeDao.deleteAll()

}