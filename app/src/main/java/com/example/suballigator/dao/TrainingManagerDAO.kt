package com.example.suballigator.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.suballigator.entity.TrainingManager

@Dao
interface TrainingManagerDAO {

    @Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    suspend fun insert(trainingManager: TrainingManager)

    @Query("SELECT * FROM TrainingManager")
    suspend fun getAll(): List<TrainingManager>

    @Query("SELECT * FROM TrainingManager WHERE id = :trainingManager_id")
    suspend fun getTrainingManagerById(trainingManager_id: Int): TrainingManager

    @Query("DELETE FROM TrainingManager")
    suspend fun deleteAll()
}