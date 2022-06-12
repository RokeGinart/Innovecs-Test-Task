package com.example.magicthegathering.presentation.fragment.start_screen

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.magicthegathering.domain.model.ui.start_screen_validation.*
import com.example.magicthegathering.domain.repositories.NavigationRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class StartScreenViewModel @Inject constructor(
    private val navigationRepository: NavigationRepository
) : ViewModel() {

    val fieldErrors = MutableSharedFlow<StartScreenValidation>()

    fun validateFields(name: String?, surname: String?, email: String?, date: String?){
        viewModelScope.launch {
            var isDataValid = true
            var nameError : NameErrors? = null
            var surnameError : SurnameErrors? = null
            var emailError : EmailErrors? = null
            var dateError : DateErrors? = null

            if(name.isNullOrEmpty()){
                nameError = NameErrors.EMPTY
                isDataValid = false
            } else if(name.length < 3){
                nameError = NameErrors.LESS_THEN_3_SYMBOLS
                isDataValid = false
            }

            if(surname.isNullOrEmpty()){
                surnameError = SurnameErrors.EMPTY
                isDataValid = false
            } else if(surname.length < 3){
                surnameError = SurnameErrors.LESS_THEN_3_SYMBOLS
                isDataValid = false
            }

            if(email.isNullOrEmpty()){
                emailError = EmailErrors.EMPTY
                isDataValid = false
            } else if(isValidEmail(email)){
                emailError = EmailErrors.INVALID_EMAIL
                isDataValid = false
            }

            if(date.isNullOrEmpty()){
                dateError = DateErrors.EMPTY
                isDataValid = false
            }

            if(!isDataValid){
                fieldErrors.emit(StartScreenValidation(nameError, surnameError, emailError, dateError))
            } else {
                navigateToMainScreen()
            }
        }

    }

    private fun navigateToMainScreen(){
        navigationRepository.navigateToMainScreen()
    }

    private fun isValidEmail(email: String): Boolean = !Patterns.EMAIL_ADDRESS.matcher(email).matches()
}