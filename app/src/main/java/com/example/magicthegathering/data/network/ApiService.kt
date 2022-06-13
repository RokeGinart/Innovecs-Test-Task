package com.example.magicthegathering.data.network

import com.example.magicthegathering.domain.model.responses.NewsListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("articles")
    suspend fun getNewsList(
        @Query("_limit") limit: Int,
    ): Response<NewsListResponse>
}