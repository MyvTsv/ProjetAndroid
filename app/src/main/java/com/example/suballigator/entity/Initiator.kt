package com.example.suballigator.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "Initiator",
    foreignKeys = [
        ForeignKey(entity = Level::class,
            parentColumns = ["id"],
            childColumns = ["levelId"])
    ])

data class Initiator(

    @PrimaryKey
    val id: Int,
    val name: String,
    val email: String,
    val password: String,
    val director: Boolean,
    val levelId: Int,
    val deleted: Boolean

) {

}