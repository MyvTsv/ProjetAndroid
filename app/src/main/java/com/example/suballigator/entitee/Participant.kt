package com.example.suballigator.entitee

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "Participant",
    foreignKeys = [ForeignKey(entity = Content::class,
        parentColumns = ["contentId"],
        childColumns = ["contentId"]),
        ForeignKey(entity = Student::class,
            parentColumns = ["studentId"],
            childColumns = ["studentId"]),
        ForeignKey(entity = Status::class,
            parentColumns = ["statusId"],
            childColumns = ["statusId"])
    ])

data class Participant(

    @PrimaryKey(autoGenerate = true)
    val participantId: Int = 1,
    val contentId: Int,
    val studentId: Int,
    val statusId: Int,
    val commentary: String

) {

}