package com.example.suballigator.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.suballigator.entity.ContainSkill

@Dao
interface ContainSkillDAO {

    @Insert
    suspend fun insert(containSkill: ContainSkill)

    @Query("SELECT * FROM ContainSkill")
    suspend fun getAll(): List<ContainSkill>

    @Query("SELECT * FROM ContainSkill WHERE containSkillId = :containSkillId")
    suspend fun getContainSkillById(containSkillId: Int): List<ContainSkill>

    @Query("DELETE FROM ContainSkill")
    suspend fun deleteAll()
}