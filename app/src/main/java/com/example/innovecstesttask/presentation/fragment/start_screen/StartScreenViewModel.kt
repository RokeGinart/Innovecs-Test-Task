package com.example.innovecstesttask.presentation.fragment.start_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.innovecstesttask.domain.model.responses.DataResponse
import com.example.innovecstesttask.domain.model.responses.DataResponseItem
import com.example.innovecstesttask.domain.model.ui.ErrorTypes
import com.example.innovecstesttask.domain.model.ui.start_screen_validation.ActionType
import com.example.innovecstesttask.domain.usecases.GetDataUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class StartScreenViewModel @Inject constructor(
    private val getDataUseCase: GetDataUseCase
) : ViewModel() {

    val errors = MutableSharedFlow<ErrorTypes>()
    val result = MutableSharedFlow<ActionType>()
    private var currentDay = 0

    fun setCurrentDay(day: Int) {
        currentDay = day
    }

    fun getAnimationData() {
        viewModelScope.launch {
            try {
                val response = getDataUseCase.execute()
                if (response.isSuccessful) {
                    response.body()?.let {
                        checkCurrentAction(it)
                    } ?: kotlin.run {
                        errors.emit(ErrorTypes.EMPTY_DATA)
                    }
                } else {
                    errors.emit(ErrorTypes.SMTH_WENT_WRONG)
                }
            } catch (exception: Exception) {
                errors.emit(ErrorTypes.SMTH_WENT_WRONG)
            }
        }
    }

    private fun checkCurrentAction(dataResponse: DataResponse) {
        viewModelScope.launch {
            val filteredAction = dataResponse
                .filter { it.enabled }
                .filter { it.valid_days.contains(currentDay) }

            val currentAction = if (filteredAction.isNotEmpty()) {
                if (filteredAction.size > 1) {
                    var priority = 0
                    var highestPriorityItem = 0
                    filteredAction.forEachIndexed { index, item ->
                        if (priority < item.priority) {
                            priority = item.priority
                            highestPriorityItem = index
                        }
                    }

                    checkActionType(filteredAction[highestPriorityItem].type)
                } else {
                    checkActionType(filteredAction[0].type)
                }
            } else {
                ActionType.None
            }

            result.emit(currentAction)
        }
    }

    private fun checkActionType(action: String): ActionType =
        when (action) {
            "animation" -> ActionType.Animation
            "toast" -> ActionType.ToastMessage
            "call" -> ActionType.Call
            "notification" -> ActionType.Notification
            else -> ActionType.None
        }
}