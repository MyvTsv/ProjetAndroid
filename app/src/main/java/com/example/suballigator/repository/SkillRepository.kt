package com.example.suballigator.repository

import com.example.suballigator.dao.SkillDAO
import com.example.suballigator.entity.Skill

class SkillRepository(private val skillDao: SkillDAO) {

    suspend fun insert(skill: Skill) = skillDao.insert(skill)

    suspend fun insert(skill: List<Skill>) {
        for (skill in skill) {
            skillDao.insert(skill)
        }
    }

    suspend fun getAll() = skillDao.getAll()

    suspend fun isExist(skill: Skill): Boolean {
        if (skillDao.getSkillById(skill.skillId) != null) {
            return true
        }
        return false
    }

    suspend fun deleteAll() = skillDao.deleteAll()

}