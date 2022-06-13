package com.example.magicthegathering.domain.usecases

import com.example.magicthegathering.domain.model.responses.NewsListResponse
import com.example.magicthegathering.domain.repositories.ApiRepository
import retrofit2.Response
import javax.inject.Inject

class GetCardsListUseCase @Inject constructor(private val apiRepository: ApiRepository) {
    suspend fun execute(limit: Int): Response<NewsListResponse> =
        apiRepository.getNewsList(limit)
}