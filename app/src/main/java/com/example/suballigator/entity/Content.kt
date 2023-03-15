package com.example.suballigator.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "Content",
    foreignKeys = [ForeignKey(entity = Session::class,
        parentColumns = ["sessionId"],
        childColumns = ["sessionId"]),
        ForeignKey(entity = Aptitude::class,
            parentColumns = ["aptitudeId"],
            childColumns = ["aptitudeId"])
    ])

data class Content(

    @PrimaryKey(autoGenerate = true)
    val contentId: Int = 1,
    val sessionId: Int,
    val aptitudeId: Int

) {

}