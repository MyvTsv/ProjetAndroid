package com.example.suballigator.entitee

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "Skill",
    foreignKeys = [ForeignKey(entity = Level::class,
        parentColumns = ["levelId"],
        childColumns = ["levelId"])
    ])

data class Skill(

    @PrimaryKey(autoGenerate = true)
    val skillId: Int = 1,
    val name: String,
    val levelId: Int,
    val deleted: Boolean

) {

}