package com.example.userapp.data.model

import androidx.room.Entity

@Entity(tableName = "users")
data class User(
    val id: Int = 0,
    val name: String,
    val age: String,
    val jobTitle: String,
    val gender: String
)
