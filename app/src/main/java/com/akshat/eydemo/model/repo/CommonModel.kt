package com.akshat.eydemo.model.repo


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavModel(
    @PrimaryKey val id: String
)