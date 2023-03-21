package com.example.suballigator.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "Session",
    foreignKeys = [
        ForeignKey(entity = Formation::class,
            parentColumns = ["id"],
            childColumns = ["formationId"])
    ])

data class Session(

    @PrimaryKey
    val id: Int,
    val date: String,
    val formationId: Int,
    val deleted:  Boolean

) {

}