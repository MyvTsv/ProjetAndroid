package com.example.suballigator.repository

import com.example.suballigator.dao.SkillDAO
import com.example.suballigator.entity.Skill

class SkillRepository(private val skillDao: SkillDAO) {

    suspend fun insert(skill: Skill) = skillDao.insert(skill)

    suspend fun getAll() = skillDao.getAll()

}