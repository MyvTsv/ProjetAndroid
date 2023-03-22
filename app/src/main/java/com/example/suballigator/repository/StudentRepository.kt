package com.example.suballigator.repository

import com.example.suballigator.dao.StudentDAO
import com.example.suballigator.entity.Student

class StudentRepository(private val studentDao: StudentDAO) {

    suspend fun insert(student: Student) = studentDao.insert(student)

    suspend fun insert(student: List<Student>) {
        for (student in student) {
            studentDao.insert(student)
        }
    }

    suspend fun getAll() = studentDao.getAll()

    suspend fun getAllNoDeleted() = studentDao.getAllNoDeleted()

    suspend fun isExist(student: Student): Boolean {
        if (studentDao.getStudentById(student.id) != null) {
            return true
        }
        return false
    }

    suspend fun deleteAll() = studentDao.deleteAll()

}