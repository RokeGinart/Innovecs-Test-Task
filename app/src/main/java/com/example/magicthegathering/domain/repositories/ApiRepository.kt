package com.example.magicthegathering.domain.repositories

import com.example.magicthegathering.domain.model.responses.NewsListResponse
import retrofit2.Response

interface ApiRepository {
    suspend fun getNewsList(limit: Int) : Response<NewsListResponse>
}