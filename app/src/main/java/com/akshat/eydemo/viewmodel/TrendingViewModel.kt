package com.akshat.eydemo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.akshat.eydemo.model.repo.FavModel
import com.example.assignment.model.repo.FavDao
import com.example.assignment.model.repo.Repository
import com.example.assignment.model.repo.RoomAppDb
import com.example.assignment.model.trandingModel.Data
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
                message.postValue("All ready there")
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
                println("Cart Error :- $e")
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
                println("Cart Error :- $e")
            }
        }
    }

}