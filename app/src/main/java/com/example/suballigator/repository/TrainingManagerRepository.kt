package com.example.suballigator.repository

import com.example.suballigator.dao.TrainingManagerDAO
import com.example.suballigator.entity.TrainingManager

class TrainingManagerRepository(private val trainingManagerDao: TrainingManagerDAO) {

    suspend fun insert(trainingManager: TrainingManager) = trainingManagerDao.insert(trainingManager)

    suspend fun insert(trainingManager: List<TrainingManager>) {
        for (trainingManager in trainingManager) {
            trainingManagerDao.insert(trainingManager)
        }
    }

    suspend fun getAll() = trainingManagerDao.getAll()

    suspend fun isExist(trainingManager: TrainingManager): Boolean {
        if (trainingManagerDao.getTrainingManagerById(trainingManager.trainingManagerId) != null) {
            return true
        }
        return false
    }

    suspend fun deleteAll() = trainingManagerDao.deleteAll()

}