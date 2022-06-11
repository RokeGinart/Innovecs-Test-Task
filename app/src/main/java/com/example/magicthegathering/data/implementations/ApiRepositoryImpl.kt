package com.example.magicthegathering.data.implementations

import com.example.magicthegathering.data.network.ApiService
import com.example.magicthegathering.domain.model.responses.CardListResponse
import com.example.magicthegathering.domain.repositories.ApiRepository
import retrofit2.Response
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(
    private val apiService: ApiService
): ApiRepository {

    override suspend fun getCardList(): Response<CardListResponse> = apiService.getCardsList()
}