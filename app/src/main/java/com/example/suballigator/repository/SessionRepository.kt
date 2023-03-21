package com.example.suballigator.repository

import com.example.suballigator.dao.SessionDAO
import com.example.suballigator.entity.Session

class SessionRepository(private val sessionDao: SessionDAO) {

    suspend fun insert(session: Session) = sessionDao.insert(session)

    suspend fun insert(session: List<Session>) {
        for (session in session) {
            sessionDao.insert(session)
        }
    }

    suspend fun getAll() = sessionDao.getAll()

    suspend fun isExist(session: Session): Boolean {
        if (sessionDao.getSessionById(session.id) != null) {
            return true
        }
        return false
    }

    suspend fun deleteAll() = sessionDao.deleteAll()

}