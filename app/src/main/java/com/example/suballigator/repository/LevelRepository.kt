package com.example.suballigator.repository

import com.example.suballigator.dao.LevelDAO
import com.example.suballigator.entity.Level

class LevelRepository(private val levelDao: LevelDAO) {

    suspend fun insert(level: Level) = levelDao.insert(level)

    suspend fun getAll() = levelDao.getAll()

}