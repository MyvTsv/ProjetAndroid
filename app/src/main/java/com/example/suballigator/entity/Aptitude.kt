package com.example.suballigator.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "Aptitude",
    foreignKeys = [
        ForeignKey(entity = Skill::class,
            parentColumns = ["id"],
            childColumns = ["skillId"])
    ])

data class Aptitude(

    @PrimaryKey
    val id: Int,
    val name: String,
    val skillId: Int,
    val deleted: Boolean

) {

}