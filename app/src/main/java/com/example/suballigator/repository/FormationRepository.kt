package com.example.suballigator.repository

import com.example.suballigator.dao.FormationDAO
import com.example.suballigator.entity.Formation

class FormationRepository(private val formationDao: FormationDAO) {

    suspend fun insert(formation: Formation) = formationDao.insert(formation)

    suspend fun getAll() = formationDao.getAll()

}