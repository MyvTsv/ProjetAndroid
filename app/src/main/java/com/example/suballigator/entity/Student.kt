package com.example.suballigator.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "Student",
    foreignKeys = [ForeignKey(entity = Formation::class,
        parentColumns = ["id"],
        childColumns = ["formationId"])
    ])

data class Student(

    @PrimaryKey
    val id: Int,
    val name: String,
    val formationId: Int,
    val phone: String,
    val deleted: Boolean

) {

}