package com.example.assignment.model.repo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.assignment.model.FavModel

@Database(entities = [FavModel::class], version = 1)
abstract class RoomAppDb : RoomDatabase() {
    abstract fun favDao(): FavDao

    companion object {
        public var INSTANCE: RoomAppDb? = null
        fun getInstance(context: Context): RoomAppDb {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder<RoomAppDb>(
                    context.applicationContext, RoomAppDb::class.java, "AppDb"
                ).allowMainThreadQueries().build()
            }
            return INSTANCE as RoomAppDb
        }
    }
}