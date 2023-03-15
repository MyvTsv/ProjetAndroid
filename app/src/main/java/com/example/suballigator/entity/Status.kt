package com.example.suballigator.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Status")
data class Status(

    @PrimaryKey(autoGenerate = true)
    val statusId:Int = 1,
    val name: String,
    val color: String

) {

}