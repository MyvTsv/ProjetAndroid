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
    var name: String,
    var email: String,
    var password: String,
    val director: Boolean,
    val levelId: Int,
    val deleted: Boolean

) {

}