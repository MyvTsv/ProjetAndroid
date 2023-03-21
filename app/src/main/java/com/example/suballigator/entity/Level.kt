package com.example.suballigator.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Level")
data class Level(

    @PrimaryKey
    val id: Int,
    val name: String,
    val deleted: Boolean

) {

}