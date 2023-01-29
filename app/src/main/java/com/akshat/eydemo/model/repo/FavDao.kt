package com.akshat.eydemo.model.repo

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.akshat.eydemo.model.FavModel


@Dao
interface FavDao {
    @Query("SELECT * FROM FavModel")
    fun getFavList(): List<FavModel>?

    @Query("SELECT * FROM FavModel WHERE id= :id")
    fun getFavSaved(id: String): List<FavModel>?

    @Insert
    fun insertFav(favModel: FavModel)

    @Delete
    fun delete(favModel: FavModel)
}