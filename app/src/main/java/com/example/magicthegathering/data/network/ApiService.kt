package com.example.magicthegathering.data.network

import com.example.magicthegathering.domain.model.responses.NewsListResponse
import com.example.magicthegathering.domain.model.responses.NewsListResponseItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("articles")
    suspend fun getNewsList(
        @Query("_limit") limit: Int,
    ): Response<NewsListResponse>

    @GET("articles/{id}")
    suspend fun getNewsDetailsById(
        @Path("id") id: Int,
    ): Response<NewsListResponseItem>
}