package com.example.suballigator.entitee

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Level")
data class Level(

    @PrimaryKey(autoGenerate = true)
    val levelId: Int = 1,
    val name: String,
    val deleted: Boolean

) {

}