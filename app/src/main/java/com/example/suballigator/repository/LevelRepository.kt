package com.example.suballigator.repository

import com.example.suballigator.dao.LevelDAO
import com.example.suballigator.entity.Level

class LevelRepository(private val levelDao: LevelDAO) {

    suspend fun insert(level: Level) = levelDao.insert(level)

    suspend fun insert(level: List<Level>) {
        for (level in level) {
            levelDao.insert(level)
        }
    }

    suspend fun getAll() = levelDao.getAll()

    suspend fun isExist(level: Level): Boolean {
        if (levelDao.getLevelById(level.levelId) != null || levelDao.getLevelByName(level.name) != null) {
            return true
        }
        return false
    }

    suspend fun deleteAll() = levelDao.deleteAll()

}