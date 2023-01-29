package com.akshat.eydemo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.akshat.eydemo.model.FavModel
import com.akshat.eydemo.model.repo.FavDao
import com.akshat.eydemo.model.repo.Repository
import com.akshat.eydemo.model.repo.RoomAppDb
import com.akshat.eydemo.model.trendingModel.Data
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TrendingViewModel : ViewModel() {
    var message = MutableLiveData("")
    var mFavDao: FavDao = RoomAppDb.INSTANCE?.favDao()!!
    var mRepository = Repository()
    var gifList = MutableLiveData<List<Data>>()

    init {
        getTrendingList()
    }

    public fun addFav(id: String) {
        var temp = mFavDao.getFavSaved(id)
        if (temp != null) {
            if (temp.isEmpty()) {
                mFavDao.insertFav(FavModel(id))
                message?.postValue("Added")
            } else {
                message.postValue("Already there!")
            }
        } else {
            mFavDao.insertFav(FavModel(id))
            message?.postValue("Added")
        }
    }

    fun getTrendingList() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                var mTrendingResponseModel =
                    mRepository.trending()
                gifList.postValue((mTrendingResponseModel.data).toMutableList())
            } catch (e: Exception) {
                println("Error :- $e")
            }
        }
    }

    fun search(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                var mTrendingResponseModel =
                    mRepository.search(query)
                gifList.postValue((mTrendingResponseModel.data).toMutableList())
            } catch (e: Exception) {
                println(" Error :- $e")
            }
        }
    }

}