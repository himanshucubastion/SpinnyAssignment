package com.spinny.assignment.model.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "auth",indices = [Index(value = ["username"], unique = true)])
data class Auth(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val username: String,
    val password: String
)