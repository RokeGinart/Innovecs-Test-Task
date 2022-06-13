package com.example.magicthegathering.domain.repositories

import com.example.magicthegathering.domain.model.responses.NewsListResponse
import com.example.magicthegathering.domain.model.responses.NewsListResponseItem
import retrofit2.Response

interface ApiRepository {
    suspend fun getNewsList(limit: Int) : Response<NewsListResponse>
    suspend fun getNewsDetailsById(id: Int): Response<NewsListResponseItem>
}