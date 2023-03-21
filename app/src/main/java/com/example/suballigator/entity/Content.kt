package com.example.suballigator.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "Content",
    foreignKeys = [
        ForeignKey(entity = Session::class,
            parentColumns = ["id"],
            childColumns = ["sessionId"]),
        ForeignKey(entity = Aptitude::class,
            parentColumns = ["id"],
            childColumns = ["aptitudeId"])
    ])

data class Content(

    @PrimaryKey
    val id: Int,
    val sessionId: Int,
    val aptitudeId: Int

) {

}