package com.example.magicthegathering.presentation.fragment.main_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.magicthegathering.domain.usecases.GetCardsListUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainScreenViewModel @Inject constructor(
    private val getCardsListUseCase : GetCardsListUseCase
) : ViewModel() {

    fun getData(){
        viewModelScope.launch {
            getCardsListUseCase.execute()
        }
    }

    override fun onCleared() {
        super.onCleared()
    }
}