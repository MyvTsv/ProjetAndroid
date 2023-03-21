package com.example.suballigator.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.suballigator.entity.Aptitude

@Dao
interface AptitudeDAO {

    @Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    suspend fun insert(aptitude: Aptitude)

    @Query("SELECT * FROM Aptitude")
    suspend fun getAll(): List<Aptitude>

    @Query("SELECT * FROM Aptitude WHERE id = :aptitudeId")
    suspend fun getAptitudeById(aptitudeId: Int): Aptitude

    @Query("DELETE FROM Aptitude")
    suspend fun deleteAll()


}