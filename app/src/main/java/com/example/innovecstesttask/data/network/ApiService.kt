package com.example.innovecstesttask.data.network

import com.example.innovecstesttask.domain.model.responses.DataResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("androidexam/butto_to_action_config.json")
    suspend fun getData(): Response<DataResponse>
}