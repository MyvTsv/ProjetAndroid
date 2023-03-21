package com.example.suballigator.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import org.jetbrains.annotations.Nullable

@Entity(tableName = "Participant",
    foreignKeys = [
        ForeignKey(entity = Content::class,
            parentColumns = ["id"],
            childColumns = ["contentId"]),
        ForeignKey(entity = Student::class,
            parentColumns = ["id"],
            childColumns = ["studentId"]),
        ForeignKey(entity = Status::class,
            parentColumns = ["id"],
            childColumns = ["statusId"])
    ])

data class Participant(

    @PrimaryKey
    val id: Int,
    val contentId: Int,
    val studentId: Int,
    val statusId: Int,
    val commentary: String?

) {

}