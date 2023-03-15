package com.example.suballigator.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "Student",
    foreignKeys = [ForeignKey(entity = Formation::class,
        parentColumns = ["formationId"],
        childColumns = ["formationId"])
    ])

data class Student(

    @PrimaryKey(autoGenerate = true)
    val studentId: Int= 1,
    val name: String,
    val formationId: Int,
    val phone: String,
    val deleted: Boolean

) {

}