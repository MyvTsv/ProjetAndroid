package com.example.suballigator.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "ContainSkill",
    foreignKeys = [
        ForeignKey(entity = Formation::class,
            parentColumns = ["id"],
            childColumns = ["formationId"]),
        ForeignKey(entity = Skill::class,
            parentColumns = ["id"],
            childColumns = ["skillId"])
    ])

data class ContainSkill(

    @PrimaryKey
    val id: Int,
    val formationId: Int,
    val skillId: Int

) {

}