package com.example.suballigator.repository

import com.example.suballigator.dao.SessionDAO
import com.example.suballigator.entity.Session

class SessionRepository(private val sessionDao: SessionDAO) {

    suspend fun insert(session: Session) = sessionDao.insert(session)

    suspend fun getAll() = sessionDao.getAll()

}