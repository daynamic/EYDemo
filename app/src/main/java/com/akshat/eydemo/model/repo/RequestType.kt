package com.example.assignment.model.repo

class RequestType {
    companion object {
        val BASE_UEL = "https://api.giphy.com/v1/gifs/"
//        val BASE_UEL = "https://api.almugeeb.ae/api/"

        //        val BASE_UEL = "http://3.22.254.82:3000/api/"

        const val TRENDING_URI = "trending"
        const val SEARCH_URI = "search"
    }
}