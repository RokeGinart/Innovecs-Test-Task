package com.example.magicthegathering.presentation.fragment.details_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.magicthegathering.utils.DateUtils
import com.example.magicthegathering.domain.model.responses.NewsListResponseItem
import com.example.magicthegathering.domain.model.ui.details_screen.UIDetailsScreenModel
import com.example.magicthegathering.domain.model.ui.main_screen.ErrorTypes
import com.example.magicthegathering.domain.usecases.GetNewsByIdUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailsScreenViewModel @Inject constructor(
    private val getNewsByIdUseCase : GetNewsByIdUseCase
) : ViewModel() {

    val errors = MutableSharedFlow<ErrorTypes>()
    val result = MutableSharedFlow<UIDetailsScreenModel>()

    fun getNewsById(id : Int){
        viewModelScope.launch {
            val response = getNewsByIdUseCase.execute(id)
            if(response.isSuccessful){
                response.body()?.let {
                    result.emit(mapResponseTiUIDetailsScreenModel(it))
                } ?: run {
                    errors.emit(ErrorTypes.EMPTY_DATA)
                }
            } else {
                errors.emit(ErrorTypes.SMTH_WENT_WRONG)
            }
        }
    }

    private fun mapResponseTiUIDetailsScreenModel(model : NewsListResponseItem) = UIDetailsScreenModel(
        title = model.title,
        description = model.summary,
        image = model.imageUrl,
        date = DateUtils.parseDate(model.publishedAt),
        publisher = model.newsSite,
    )
}