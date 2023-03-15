package com.example.suballigator.repository

import com.example.suballigator.dao.ContainSkillDAO
import com.example.suballigator.entity.ContainSkill

class ContainSkillRepository(private val containSkillDao: ContainSkillDAO) {

    suspend fun insert(containSkill: ContainSkill) = containSkillDao.insert(containSkill)

    suspend fun getAll() = containSkillDao.getAll()

}
