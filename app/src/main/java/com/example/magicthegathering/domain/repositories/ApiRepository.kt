package com.example.magicthegathering.domain.repositories

import com.example.magicthegathering.domain.model.responses.CardListResponse
import retrofit2.Response

interface ApiRepository {
    suspend fun getCardList() : Response<CardListResponse>
}