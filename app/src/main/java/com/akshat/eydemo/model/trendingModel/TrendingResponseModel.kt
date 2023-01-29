package com.akshat.eydemo.model.trendingModel


data class TrendingResponseModel(
    val `data`: List<Data>,
    val meta: Meta,
    val pagination: Pagination
)