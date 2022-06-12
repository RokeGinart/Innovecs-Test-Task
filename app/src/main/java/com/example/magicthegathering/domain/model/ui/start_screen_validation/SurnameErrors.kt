package com.example.magicthegathering.domain.model.ui.start_screen_validation

import androidx.annotation.StringRes
import com.example.magicthegathering.R

enum class SurnameErrors(@StringRes val error : Int) {
    EMPTY(R.string.surname_empty_validation),
    LESS_THEN_3_SYMBOLS(R.string.surname_less_then_3_symbols_validation)
}