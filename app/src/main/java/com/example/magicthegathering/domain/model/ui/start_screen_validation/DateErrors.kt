package com.example.magicthegathering.domain.model.ui.start_screen_validation

import androidx.annotation.StringRes
import com.example.magicthegathering.R

enum class DateErrors(@StringRes val error : Int) {
    EMPTY(R.string.date_empty_validation)
}