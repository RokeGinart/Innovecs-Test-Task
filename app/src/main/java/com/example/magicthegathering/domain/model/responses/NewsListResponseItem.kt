package com.example.magicthegathering.domain.model.responses

data class NewsListResponseItem(
    val events: List<Event>,
    val featured: Boolean,
    val id: Int,
    val imageUrl: String,
    val launches: List<Launche>,
    val newsSite: String,
    val publishedAt: String,
    val summary: String,
    val title: String,
    val updatedAt: String,
    val url: String
)