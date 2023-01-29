package com.akshat.eydemo

import android.app.Application
import com.example.assignment.model.repo.RoomAppDb

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        RoomAppDb.getInstance(this)
    }
}