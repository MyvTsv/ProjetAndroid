package com.example.suballigator.repository

import com.example.suballigator.dao.ContentDAO
import com.example.suballigator.entity.Content

class ContentRepository(private val contentDao: ContentDAO) {

        suspend fun insert(content: Content) = contentDao.insert(content)

        suspend fun insert(content: List<Content>) {
            for (content in content) {
                contentDao.insert(content)
            }
        }

        suspend fun getAll() = contentDao.getAll()

        suspend fun getContentById(contentId: Int) = contentDao.getContentById(contentId)

        suspend fun getContentBySessionId(sessionId: Int) = contentDao.getContentBySessionId(sessionId)

        suspend fun isExist(content: Content): Boolean {
            if (contentDao.getContentById(content.id) != null) {
                return true
            }
            return false
        }

        suspend fun deleteAll() = contentDao.deleteAll()
}