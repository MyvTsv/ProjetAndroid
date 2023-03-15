package com.example.suballigator.repository

import com.example.suballigator.dao.ContentDAO
import com.example.suballigator.entity.Content

class ContentRepositoty(private val contentDao: ContentDAO) {

        suspend fun insert(content: Content) = contentDao.insert(content)

        suspend fun getAll() = contentDao.getAll()
}