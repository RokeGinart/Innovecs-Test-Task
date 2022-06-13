package com.example.magicthegathering.presentation.fragment.main_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.magicthegathering.utils.DateUtils
import com.example.magicthegathering.domain.model.responses.NewsListResponseItem
import com.example.magicthegathering.domain.model.ui.main_screen.ErrorTypes
import com.example.magicthegathering.domain.model.ui.main_screen.UINewsModel
import com.example.magicthegathering.domain.repositories.NavigationRepository
import com.example.magicthegathering.domain.usecases.GetCardsListUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainScreenViewModel @Inject constructor(
    private val getCardsListUseCase: GetCardsListUseCase,
    private val navigationRepository: NavigationRepository
) : ViewModel() {

    val errors = MutableSharedFlow<ErrorTypes>()
    val result = MutableSharedFlow<List<UINewsModel>>()
    private var limit = 20

    fun getData() {
        viewModelScope.launch {
            val response = getCardsListUseCase.execute(limit)
            if (response.isSuccessful) {
                response.body()?.let {
                    val cardList = arrayListOf<UINewsModel>()
                    it.forEach{ news ->
                        cardList.add(mapResponseToUICardModel(news))
                    }

                    result.emit(cardList)
                } ?: run {
                    errors.emit(ErrorTypes.EMPTY_DATA)
                }
            } else {
                errors.emit(ErrorTypes.SMTH_WENT_WRONG)
            }
        }
    }

    private fun mapResponseToUICardModel(news: NewsListResponseItem) = UINewsModel(
        id = news.id,
        title = news.title,
        newsSite = news.newsSite,
        publishDate = DateUtils.parseDate(news.publishedAt),
        image = news.imageUrl,
    )

    fun navigateToNewsDetails(id : Int){
        navigationRepository.navigateToDetailsScreen(id)
    }
}