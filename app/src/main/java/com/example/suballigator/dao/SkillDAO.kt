package com.example.suballigator.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.suballigator.entity.Skill

@Dao
interface SkillDAO {

    @Insert
    suspend fun insert(skill: Skill)

    @Query("SELECT * FROM Skill")
    suspend fun getAll(): List<Skill>

}