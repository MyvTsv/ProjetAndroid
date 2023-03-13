package com.example.suballigator.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.suballigator.entitee.Student

@Dao
interface StudentDAO {

    @Insert
    suspend fun insert(student: Student)

    @Query("SELECT * FROM Student")
    suspend fun getAll(): List<Student>

}