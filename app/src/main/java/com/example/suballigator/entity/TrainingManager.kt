package com.example.suballigator.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "TrainingManager",
    foreignKeys = [ForeignKey(entity = Formation::class,
        parentColumns = ["id"],
        childColumns = ["formationId"]),
        ForeignKey(entity = Initiator::class,
            parentColumns = ["id"],
            childColumns = ["initiatorId"])
    ])

data class TrainingManager(

    @PrimaryKey
    val id: Int,
    val initiatorId: Int,
    val formationId: Int

) {

}