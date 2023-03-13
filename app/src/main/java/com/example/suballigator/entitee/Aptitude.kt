package com.example.suballigator.entitee

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "Aptitude",
    foreignKeys = [ForeignKey(entity = Skill::class,
        parentColumns = ["aptitudeId"],
        childColumns = ["aptitudeId"])
    ])

data class Aptitude(

    @PrimaryKey(autoGenerate = true)
    val aptitudeId: Int = 1,
    val name: String,
    val skillId: Int,
    val deleted: Boolean

) {

}