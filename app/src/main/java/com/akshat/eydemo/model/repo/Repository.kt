package com.akshat.eydemo.model.repo

class Repository {
    var apiService = BaseRetrofit.getApiService()
    suspend fun trending() = apiService.trending(
        "55h00gM4QIHbYXmYRJlRCbY64IBk7y3u", "30", "5", "g",
        "e826c9fc5c929e0d6c6d423841a282aa",
        "messaging_non_clips"
    )

    suspend fun search(query: String) = apiService.search(
        "55h00gM4QIHbYXmYRJlRCbY64IBk7y3u",query, "30", "5", "g",
        "e826c9fc5c929e0d6c6d423841a282aa",
        "messaging_non_clips"
    )
}