package com.example.innovecstesttask.data.implementations

import com.example.innovecstesttask.data.network.ApiService
import com.example.innovecstesttask.domain.model.responses.DataResponse
import com.example.innovecstesttask.domain.repositories.ApiRepository
import retrofit2.Response
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : ApiRepository {

    override suspend fun getData(): Response<DataResponse> = apiService.getData()
}