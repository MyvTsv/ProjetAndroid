package com.example.suballigator.repository

import com.example.suballigator.dao.ContainSkillDAO
import com.example.suballigator.entity.ContainSkill

class ContainSkillRepository(private val containSkillDao: ContainSkillDAO) {

    suspend fun insert(containSkill: ContainSkill) = containSkillDao.insert(containSkill)

    suspend fun insert(containSkill: List<ContainSkill>) {
        for (containSkill in containSkill) {
            containSkillDao.insert(containSkill)
        }
    }

    suspend fun getAll() = containSkillDao.getAll()

    suspend fun isExist(containSkill: ContainSkill): Boolean {
        if (containSkillDao.getContainSkillById(containSkill.containSkillId) != null) {
            return true
        }
        return false
    }

    suspend fun deleteAll() = containSkillDao.deleteAll()

}
