package com.example.suballigator.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.suballigator.entity.TrainingManager

@Dao
interface TrainingManagerDAO {

    @Insert
    suspend fun insert(trainingManager: TrainingManager)

    @Query("SELECT * FROM TrainingManager")
    suspend fun getAll(): List<TrainingManager>
}