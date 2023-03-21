package com.example.suballigator.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.suballigator.entity.Level

@Dao
interface LevelDAO {

    @Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    suspend fun insert(level: Level)

    @Query("SELECT * FROM Level")
    suspend fun getAll(): List<Level>

    @Query("SELECT * FROM Level WHERE id = :level_id")
    suspend fun getLevelById(level_id: Int): Level

    @Query("SELECT * FROM Level WHERE name = :name")
    suspend fun getLevelByName(name: String): Level

    @Query("DELETE FROM Level")
    suspend fun deleteAll()

}