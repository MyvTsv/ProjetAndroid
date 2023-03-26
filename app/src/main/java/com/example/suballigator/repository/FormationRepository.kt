package com.example.suballigator.repository

import com.example.suballigator.dao.FormationDAO
import com.example.suballigator.entity.Formation

class FormationRepository(private val formationDao: FormationDAO) {

    suspend fun insert(formation: Formation) = formationDao.insert(formation)

    suspend fun insert(formation: List<Formation>) {
        for (formation in formation) {
            formationDao.insert(formation)
        }
    }

    suspend fun getAll() = formationDao.getAll()

    suspend fun getFormationById(id: Int) = formationDao.getFormationById(id)

    suspend fun isExist(formation: Formation): Boolean {
        if (formationDao.getFormationById(formation.id) != null) {
            return true
        }
        return false
    }

    suspend fun deleteAll() = formationDao.deleteAll()

}