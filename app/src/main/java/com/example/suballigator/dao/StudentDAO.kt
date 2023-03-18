package com.example.suballigator.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.suballigator.entity.Student

@Dao
interface StudentDAO {

    @Insert
    suspend fun insert(student: Student)

    @Query("SELECT * FROM Student")
    suspend fun getAll(): List<Student>

    @Query("SELECT * FROM Student WHERE studentId = :student_id")
    suspend fun getStudentById(student_id: Int): Student

    @Query("DELETE FROM Student")
    suspend fun deleteAll()

}