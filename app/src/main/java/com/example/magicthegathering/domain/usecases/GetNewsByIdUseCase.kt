package com.example.magicthegathering.domain.usecases

import com.example.magicthegathering.domain.model.responses.NewsListResponseItem
import com.example.magicthegathering.domain.repositories.ApiRepository
import retrofit2.Response
import javax.inject.Inject

class GetNewsByIdUseCase @Inject constructor(private val apiRepository: ApiRepository) {
    suspend fun execute(id: Int): Response<NewsListResponseItem> =
        apiRepository.getNewsDetailsById(id)
}