package com.akshat.eydemo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.akshat.eydemo.model.FavModel
import com.akshat.eydemo.model.repo.FavDao
import com.akshat.eydemo.model.repo.RoomAppDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavViewModel() : ViewModel() {
    var mFavList: MutableLiveData<List<FavModel>> = MutableLiveData()
    var mFavDao: FavDao = RoomAppDb.INSTANCE?.favDao()!!
    var message = MutableLiveData("")

    public fun getFavList() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                mFavList.postValue(mFavDao.getFavList())
            } catch (e: Exception) {
            }
        }
    }

    public fun delete(id: String) {
        var temp = mFavDao.getFavSaved(id)
        if (temp != null) {
            if (temp.isNotEmpty())
                mFavDao.delete(FavModel(id))
        } else {
            message.postValue("There is not any item")
        }
    }
}