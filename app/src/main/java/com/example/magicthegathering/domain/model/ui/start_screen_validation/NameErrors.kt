package com.example.magicthegathering.domain.model.ui.start_screen_validation

import androidx.annotation.StringRes
import com.example.magicthegathering.R

enum class NameErrors(@StringRes val error : Int) {
    EMPTY(R.string.name_empty_validation),
    LESS_THEN_3_SYMBOLS(R.string.name_less_then_3_symbols_validation)
}