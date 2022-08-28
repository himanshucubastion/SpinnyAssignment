package com.spinny.assignment.model.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "car",
    indices = [Index(value = ["modelName", "makeCar", "userId"], unique = true)]
)
data class MyCars(
    var modelName: String?,
    val makeCar: String?,
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val userId: Int
)