package com.example.suballigator.entitee

import androidx.room.Delete
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "Formation",
    foreignKeys = [ForeignKey(entity = Level::class,
        parentColumns = ["levelId"],
        childColumns = ["levelId"])
    ])

data class Formation(

    @PrimaryKey(autoGenerate = true)
    val formationId: Int = 1,
    val name: String,
    val levelId: Int,
    val delete: Boolean

) {

}