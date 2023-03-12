package com.example.suballigator.entitee

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "Initiator",
    foreignKeys = [ForeignKey(entity = Level::class,
        parentColumns = ["levelId"],
        childColumns = ["levelId"])
    ])

data class Initiator(

    @PrimaryKey(autoGenerate = true)
    val initiatorId: Int = 1,
    val name: String,
    val email: String,
    val password: String,
    val director: Boolean,
    val levelId: Int,
    val deleted: Boolean

) {

}