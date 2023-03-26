package com.example.suballigator.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.suballigator.entity.Student

@Dao
interface StudentDAO {

    @Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    suspend fun insert(student: Student)

    @Query("SELECT * FROM Student ORDER BY name")
    suspend fun getAll(): List<Student>

    @Query("SELECT * FROM Student WHERE deleted = false")
    suspend fun getAllNoDeleted(): List<Student>

    @Query("SELECT * FROM Student WHERE formationId = :id")
    suspend fun getStudentByFormationId(id: Int): Student

    @Query("SELECT * FROM Student WHERE id = :student_id")
    suspend fun getStudentById(student_id: Int): Student

    @Query("DELETE FROM Student")
    suspend fun deleteAll()

}