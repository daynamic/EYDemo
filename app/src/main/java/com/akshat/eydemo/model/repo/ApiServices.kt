package com.akshat.eydemo.model.repo


import com.akshat.eydemo.model.repo.RequestType.Companion.SEARCH_URI
import com.akshat.eydemo.model.repo.RequestType.Companion.TRENDING_URI
import com.akshat.eydemo.model.trendingModel.TrendingResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    @GET(TRENDING_URI)
    suspend fun trending(
        @Query("api_key") api: String,
        @Query("limit") limit: String,
        @Query("offset") offset: String,
        @Query("rating") rating: String,
        @Query("random_id") random_id: String,
        @Query("bundle") bundle: String,
    ): TrendingResponseModel

    @GET(SEARCH_URI)
    suspend fun search(
        @Query("api_key") api: String,
        @Query("q") q: String,
        @Query("limit") limit: String,
        @Query("offset") offset: String,
        @Query("rating") rating: String,
        @Query("random_id") random_id: String,
        @Query("bundle") bundle: String,
    ): TrendingResponseModel

}