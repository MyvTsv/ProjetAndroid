package com.example.suballigator.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "Formation",
    foreignKeys = [
        ForeignKey(entity = Level::class,
            parentColumns = ["id"],
            childColumns = ["levelId"])
    ])

data class Formation(

    @PrimaryKey
    val id: Int,
    val name: String,
    val levelId: Int,
    val delete: Boolean

) {

}