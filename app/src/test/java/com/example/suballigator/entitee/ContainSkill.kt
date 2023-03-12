package com.example.suballigator.entitee

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "ContainSkill",
    foreignKeys = [ForeignKey(entity = Formation::class,
        parentColumns = ["formationId"],
        childColumns = ["formationId"]),
        ForeignKey(entity = Skill::class,
        parentColumns = ["skillId"],
        childColumns = ["skillId"])
    ])

data class ContainSkill(

    @PrimaryKey(autoGenerate = true)
    val containSkillId: Int = 1,
    val formationId: Int,
    val skillId: Int

) {

}