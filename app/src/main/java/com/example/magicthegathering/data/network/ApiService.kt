package com.example.magicthegathering.data.network

import com.example.magicthegathering.domain.model.responses.CardListResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("/cards")
    suspend fun getCardsList() : Response<CardListResponse>
}