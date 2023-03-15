package com.example.suballigator.repository

import com.example.suballigator.dao.StudentDAO
import com.example.suballigator.entity.Student

class StudentRepository(private val studentDao: StudentDAO) {

    suspend fun insert(student: Student) = studentDao.insert(student)

    suspend fun getAll() = studentDao.getAll()

}