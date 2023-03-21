package com.example.suballigator.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Status")
data class Status(

    @PrimaryKey
    val id:Int,
    val name: String,
    val color: String

) {

}