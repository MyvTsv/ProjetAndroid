package com.example.suballigator.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "Session",
    foreignKeys = [ForeignKey(entity = Formation::class,
        parentColumns = ["formationId"],
        childColumns = ["formationId"])
    ])

data class Session(

    @PrimaryKey(autoGenerate = true)
    val sessionId: Int = 1,
    val date: String,
    val formationId: Int,
    val deleted:  Boolean

) {

}