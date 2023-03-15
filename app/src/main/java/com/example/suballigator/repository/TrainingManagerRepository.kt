package com.example.suballigator.repository

import com.example.suballigator.dao.TrainingManagerDAO
import com.example.suballigator.entity.TrainingManager

class TrainingManagerRepository(private val trainingManagerDao: TrainingManagerDAO) {

    suspend fun insert(trainingManager: TrainingManager) = trainingManagerDao.insert(trainingManager)

    suspend fun getAll() = trainingManagerDao.getAll()

}