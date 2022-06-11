package com.example.magicthegathering.domain.usecases

import android.util.Log
import com.example.magicthegathering.domain.model.responses.CardListResponse
import com.example.magicthegathering.domain.repositories.ApiRepository
import retrofit2.Response
import javax.inject.Inject

class GetCardsListUseCase @Inject constructor(private val apiRepository: ApiRepository) {
    suspend fun execute() : Response<CardListResponse> {
        Log.d("TAGS", "WORKS")
        return apiRepository.getCardList()
    }
}