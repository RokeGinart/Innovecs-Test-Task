package com.example.innovecstesttask.domain.usecases

import com.example.innovecstesttask.domain.model.responses.DataResponse
import com.example.innovecstesttask.domain.repositories.ApiRepository
import retrofit2.Response
import javax.inject.Inject

class GetDataUseCase @Inject constructor(private val apiRepository: ApiRepository) {
    suspend fun execute(): Response<DataResponse> = apiRepository.getData()
}