package com.example.suballigator.repository

import com.example.suballigator.dao.AptitudeDAO
import com.example.suballigator.entity.Aptitude

class AptitudeRepository(private val aptitudeDao: AptitudeDAO) {

    suspend fun insert(aptitude: Aptitude) = aptitudeDao.insert(aptitude)

    suspend fun getAll() = aptitudeDao.getAll()

}