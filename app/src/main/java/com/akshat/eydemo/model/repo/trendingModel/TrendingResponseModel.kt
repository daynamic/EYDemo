package com.example.assignment.model.trandingModel

data class TrendingResponseModel(
    val `data`: List<Data>,
    val meta: Meta,
    val pagination: Pagination
)