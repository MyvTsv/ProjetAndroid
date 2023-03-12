package com.example.suballigator.entitee

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "TrainingManager",
    foreignKeys = [ForeignKey(entity = Formation::class,
        parentColumns = ["formationId"],
        childColumns = ["formationId"]),
        ForeignKey(entity = Initiator::class,
            parentColumns = ["initiatorId"],
            childColumns = ["initiatorId"])
    ])

data class TrainingManager(

    @PrimaryKey(autoGenerate = true)
    val trainingManagerId: Int = 1,
    val initiatorId: Int,
    val formationId: Int

) {

}