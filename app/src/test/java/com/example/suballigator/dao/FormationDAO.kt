package com.example.suballigator.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.suballigator.entitee.Formation

@Dao
interface FormationDAO {

    @Insert
    suspend fun insert(formation: Formation)

    @Query("SELECT * FROM Formation")
    suspend fun getAll(): List<Formation>

}