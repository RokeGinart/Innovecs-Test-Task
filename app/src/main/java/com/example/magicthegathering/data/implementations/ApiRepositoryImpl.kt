package com.example.magicthegathering.data.implementations

import com.example.magicthegathering.data.network.ApiService
import com.example.magicthegathering.domain.model.responses.NewsListResponse
import com.example.magicthegathering.domain.model.responses.NewsListResponseItem
import com.example.magicthegathering.domain.repositories.ApiRepository
import retrofit2.Response
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : ApiRepository {
    override suspend fun getNewsList(limit: Int): Response<NewsListResponse> =
        apiService.getNewsList(limit)

    override suspend fun getNewsDetailsById(id: Int): Response<NewsListResponseItem> =
        apiService.getNewsDetailsById(id)
}