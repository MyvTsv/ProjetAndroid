package com.example.suballigator.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "Skill",
    foreignKeys = [
        ForeignKey(entity = Level::class,
            parentColumns = ["id"],
            childColumns = ["levelId"])
    ])

data class Skill(

    @PrimaryKey
    val id: Int,
    val name: String,
    val levelId: Int,
    val deleted: Boolean

) {

}