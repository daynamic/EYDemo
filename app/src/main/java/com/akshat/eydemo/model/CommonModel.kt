package com.akshat.eydemo.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavModel(
    @PrimaryKey val id: String
)