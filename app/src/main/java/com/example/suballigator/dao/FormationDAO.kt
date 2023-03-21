package com.example.suballigator.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.suballigator.entity.Formation

@Dao
interface FormationDAO {

    @Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    suspend fun insert(formation: Formation)

    @Query("SELECT * FROM Formation")
    suspend fun getAll(): List<Formation>

    @Query("SELECT * FROM Formation WHERE id = :formation_id")
    suspend fun getFormationById(formation_id: Int): Formation

    @Query("DELETE FROM Formation")
    suspend fun deleteAll()

}