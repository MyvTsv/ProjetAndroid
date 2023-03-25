package com.example.suballigator.dao

import android.app.Application
import androidx.room.*
import com.example.suballigator.AppDatabase
import com.example.suballigator.entity.Initiator

@Dao
interface InitiatorDAO {

    @Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    suspend fun insert(initiator: Initiator)

    @Query("SELECT * FROM Initiator")
    suspend fun getAll(): List<Initiator>

    @Query("SELECT * FROM Initiator WHERE id = :initiator_id")
    suspend fun getInitiatorById(initiator_id: Int): Initiator

    @Query("DELETE FROM Initiator")
    suspend fun deleteAll()

    @Update
    suspend fun update(initiator: Initiator)

    @Transaction
    suspend fun updateInitiatorLevel(initiatorId: Int, levelId: Int, a: Application) {
        val initiator = getInitiatorById(initiatorId)
        val level = AppDatabase.getDatabase(a).levelDAO().getLevelById(levelId)
        if (initiator != null && level != null) {
            initiator.levelId = level.id
            update(initiator)
        }
    }

}