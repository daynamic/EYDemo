package com.example.assignment.model.repo

import retrofit2.http.Query

class Repository {
    var apiService = BaseRetrofit.getApiService()
    suspend fun trending() = apiService.trending(
        "fh1ZOH6VNR5tFt8VOIiCtAp50uh4cLc5", "30", "5", "g",
        "e826c9fc5c929e0d6c6d423841a282aa",
        "messaging_non_clips"
    )

    suspend fun search(query: String) = apiService.search(
        "fh1ZOH6VNR5tFt8VOIiCtAp50uh4cLc5",query, "30", "5", "g",
        "e826c9fc5c929e0d6c6d423841a282aa",
        "messaging_non_clips"
    )
}