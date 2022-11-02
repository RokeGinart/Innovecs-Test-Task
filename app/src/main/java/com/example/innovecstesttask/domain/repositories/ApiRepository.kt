package com.example.innovecstesttask.domain.repositories

import com.example.innovecstesttask.domain.model.responses.DataResponse
import retrofit2.Response

interface ApiRepository {
    suspend fun getData(): Response<DataResponse>
}